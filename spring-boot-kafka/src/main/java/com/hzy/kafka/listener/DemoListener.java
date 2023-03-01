package com.hzy.kafka.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class DemoListener {

    @KafkaListener(id = "myId", topics = "topic1")
    public void listen(String in) throws InterruptedException {
        System.out.println("111-消费消息" + "-" + in);
        Thread.sleep(300);
    }

    @KafkaListener(id = "myId2", topics = "topic1")
    public void listen2(ConsumerRecord<String, String> record) throws InterruptedException {
        System.out.println("222-消费消息" + "-" + record.topic() + "-" + record.value());
        Thread.sleep(100);
    }

}
