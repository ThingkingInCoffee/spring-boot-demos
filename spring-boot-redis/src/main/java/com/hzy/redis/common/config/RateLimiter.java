package com.hzy.redis.common.config;

import io.netty.buffer.ByteBufUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.security.MessageDigest;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 * 请求第三方限流判断，限定一段时间内请求次数不能超过N次
 */
@Component
@Slf4j
public class RateLimiter {

    @Resource
    StringRedisTemplate stringRedisTemplate;

    private static final String REDIS_KEY_PREFIX = "THIRD:REQ:RATE_LIMITER:";

    /**
     * @param source     资源
     * @param limit      限制值
     * @param period     间隔
     * @param periodUnit 间隔时间单位
     * @return 是否允许
     */
    public boolean isAllow(String source, int limit, long period, TimeUnit periodUnit) {
        if (StringUtils.isEmpty(source)) {
            log.warn("isAllow 参数为空");
            return false;
        }
        String key = REDIS_KEY_PREFIX + source;
        long now = System.currentTimeMillis();
        final long periodMillis = TimeUnit.MILLISECONDS.convert(period, periodUnit);
        final String result = stringRedisTemplate.execute(RateLimiterScript.instance, Collections.singletonList(key), String.valueOf(limit), String.valueOf(now), String.valueOf(periodMillis));
        return result != null && result.equals("1");
    }


    static class RateLimiterScript implements RedisScript<String> {
        static RateLimiterScript instance = new RateLimiterScript();
        static String shaCache;

        @Override
        public String getSha1() {
            if (shaCache != null)
                return shaCache;
            try {
                MessageDigest mdigest = MessageDigest.getInstance("SHA-1");
                byte[] s = mdigest.digest(getScriptAsString().getBytes());
                shaCache = ByteBufUtil.hexDump(s);
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
            return shaCache;
        }

        @Override
        public Class<String> getResultType() {
            return String.class;
        }

        @Override
        public String getScriptAsString() {
            return "redis.call('ZREMRANGEBYSCORE', KEYS[1], 0.0, tonumber(ARGV[2]) - tonumber(ARGV[3])) " +
                    "if (redis.call('ZCARD', KEYS[1]) >= tonumber(ARGV[1])) then " +
                    "   return '0'" +
                    "end " +
                    "redis.call('ZADD', KEYS[1], ARGV[2], ARGV[2]) " +
                    "redis.call('pexpire', KEYS[1], ARGV[3]) " +
                    "return '1'";
        }
    }
}

