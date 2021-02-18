package com.hzy.rabbitMQ.sender;

import com.hzy.rabbitMQ.RabbitMQApplication;
import com.hzy.rabbitMQ.bean.MsgBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RabbitMQApplication.class)
public class RabbitMQSenderServiceTest {

    @Autowired
    private RabbitMQSenderService rabbitMQSenderService;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送简单消息
     * @throws Exception
     */
    @Test
    public void sendMessage() throws Exception {
        rabbitMQSenderService.sendMessage();
    }

    /**
     * 测试交换机错误
     * @throws Exception
     */
    @Test
    public void sendMessageWithCorrelationData() throws Exception {
        CorrelationData data = new CorrelationData();
        data.setId("135246");
        rabbitMQSenderService.sendMessage("noThisExchange", "demoKey", "sendMessageWithWrongEx()", data);
    }

    /**
     * 测试交换机错误
     * @throws Exception
     */
    @Test
    public void sendMessageWithWrongEx() throws Exception {
        rabbitMQSenderService.sendMessage("noThisExchange", "demoKey", "sendMessageWithWrongEx()");
    }


    @Test
    public void sendConcurrencyMessageWithWrongEx() {
        for (int i = 0; i < 10000; i++) {
            rabbitTemplate.convertAndSend("ConcurrencyDemoExchange", "ConcurrencyDemoKey", i + "");
        }
    }

}