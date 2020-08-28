package com.hzy.rabbitMQ.receiver;

import com.alibaba.fastjson.JSON;
import com.hzy.rabbitMQ.bean.DemoMessage;
import com.hzy.rabbitMQ.common.constant.QueueConstant;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class DemoReceiver {

    @RabbitListener(queues = QueueConstant.DEMO_QUEUE)
    public void onMessage(DemoMessage msgString, @Header(AmqpHeaders.CONTENT_TYPE) String contentType, Channel channel, Message message) throws IOException {
        log.info("onMessage 接收自定义消息对象[{}], CONTENT_TYPE [{}], message消息对象[{}]", msgString, contentType, JSON.toJSONString(message));
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        channel.basicAck(deliveryTag, false);
    }

}
