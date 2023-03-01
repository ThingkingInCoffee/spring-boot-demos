package com.hzy.kafka.service;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Slf4j
@Service
public class DemoProducer {

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendDemoMsg() {
        final String format = DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i < 1000; i++) {
            kafkaTemplate.send("topic1", format + " -- " + i);
        }
        log.info("发送完成");
    }

}
