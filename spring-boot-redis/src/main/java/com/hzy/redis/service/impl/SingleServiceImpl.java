package com.hzy.redis.service.impl;

import com.hzy.redis.service.SingleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class SingleServiceImpl implements SingleService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void connect() {
        redisTemplate.opsForValue().set("single", 1111);
    }
}
