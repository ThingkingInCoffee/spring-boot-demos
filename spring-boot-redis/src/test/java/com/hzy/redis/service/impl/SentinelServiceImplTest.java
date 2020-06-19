package com.hzy.redis.service.impl;

import com.hzy.redis.service.SentinelService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SentinelServiceImplTest {
    @Autowired
    private SentinelService sentinelService;

    @Test
    public void connect() {
        sentinelService.connect();
    }
}