package com.hzy.kafka.service;

import com.hzy.kafka.SpringBootKafkaApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootKafkaApplication.class)
public class DemoProducerTest {

    @Resource
    private DemoProducer demoProducer;

    @Test
    public void test() {
        demoProducer.sendDemoMsg();
    }

}