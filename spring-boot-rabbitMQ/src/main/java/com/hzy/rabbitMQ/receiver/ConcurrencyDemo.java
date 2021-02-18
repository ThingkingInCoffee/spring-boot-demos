package com.hzy.rabbitMQ.receiver;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 测试并发处理消息
 */
@Slf4j
@Component
public class ConcurrencyDemo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "ConcurrencyDemo"),
            key = "ConcurrencyDemoKey",
            exchange = @Exchange(value = "ConcurrencyDemoExchange")
    ))
    @RabbitHandler()
    public void onMessage(String msg, Channel channel, Message message) throws IOException {
        String threadName = Thread.currentThread().getId() + "";
        log.info("ConcurrencyDemo 接收消息对象[{}], 线程名称[{}]", msg, threadName);
        jdbcTemplate.execute("INSERT INTO `demo`.`test_concurrency` (`thread_name`, `msg`) VALUES ( " + threadName + "," + msg + ")");
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        channel.basicAck(deliveryTag, false);
    }

}
