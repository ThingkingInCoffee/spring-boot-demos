package com.hzy.rabbitMQ.sender;

import com.hzy.rabbitMQ.RabbitMQApplication;
import com.hzy.rabbitMQ.bean.DemoMessage;
import com.hzy.rabbitMQ.common.constant.ExchangeConstant;
import com.hzy.rabbitMQ.common.constant.RoutingKeyConstant;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RabbitMQApplication.class)
class DirectSenderServiceImplTest {

    @Autowired
    private DirectSenderService directSenderService;

    private DemoMessage getDemoMessage() {
        int random = (int) (Math.random() * 100);
        DemoMessage message = new DemoMessage("sendMessage" + random, 1, new BigDecimal(Integer.toString(random)));
        return message;
    }


    @Test
    void sendMessage() {
        DemoMessage demoMessage = getDemoMessage();
        directSenderService.sendMessage(ExchangeConstant.DIRECT_EXCHANGE, RoutingKeyConstant.DIRECT_ROUTING_KEY, demoMessage);
    }

    @Test
    void sendMessage1() {
    }

    @Test
    void sendMessage2() {
    }

    @Test
    void sendMessage3() {
    }
}