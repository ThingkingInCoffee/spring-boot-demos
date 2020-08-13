package com.hzy.redis.service.impl;

import com.hzy.redis.service.SingleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;

@Slf4j
@Service
public class SingleServiceImpl implements SingleService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void connect() {
        redisTemplate.opsForValue().set("single", 1111);
    }

    @Cacheable(cacheNames = "ing")
    public Integer getValid(String param) {
        log.info("do query getValid");
        return 1;
    }

    @Cacheable(cacheNames = "pre")
    public Integer getPreValid(String param) {
        return 2;
    }

    //    @CachePut(cacheNames = "ing")
    public Integer setValid(String param, Integer val) {
        log.info("do update setValid");
        redisTemplate.opsForValue().set("test::" + param, val);
        return val;
    }

    @Override
    @CachePut(cacheNames = "ing")
    public Integer setValid(String param) {
        log.info("do update setValid random");
        return (int) (Math.random() * 100);
    }

}
