package com.hzy.redis.service.impl;

import com.hzy.redis.common.config.RateLimiter;
import com.hzy.redis.service.SentinelService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.ScriptSource;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class SentinelServiceImplTest {
    @Autowired
    private SentinelService sentinelService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void connect() {
        sentinelService.connect();
    }

    @Resource
    private RateLimiter rateLimiter;

    @Test
    public void test013() {
        boolean test = rateLimiter.isAllow("test", 20, 60, TimeUnit.SECONDS);
        log.info("isAllow[{}]", test);
    }

    @Test
    public void test015() {
        ScriptSource scriptSource = new ResourceScriptSource(new ClassPathResource("scripts/limit.lua"));
        DefaultRedisScript defaultRedisScript = new DefaultRedisScript();
        defaultRedisScript.setScriptSource(scriptSource);
        defaultRedisScript.setResultType(String.class);

        final Object execute = stringRedisTemplate.execute(defaultRedisScript, Arrays.asList("testDemo:inc"), "1", System.currentTimeMillis() + "", "60000");
        log.info("tets   {}", execute);
    }
}