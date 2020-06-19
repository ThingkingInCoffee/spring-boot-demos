package com.hzy.redis.service.impl;

import com.hzy.redis.service.SentinelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class SentinelServiceImpl implements SentinelService {

    private boolean flag = true;

    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public void connect() {
        int i = 0;
        while (flag) {
            redisTemplate.opsForValue().set("sentinel"+ ++i, 7201 + i);
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (i > 500) {
                flag = false;
            }
        }

    }
}
