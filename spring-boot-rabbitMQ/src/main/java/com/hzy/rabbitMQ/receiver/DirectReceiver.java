package com.hzy.rabbitMQ.receiver;

import com.alibaba.fastjson.JSON;
import com.hzy.rabbitMQ.bean.DemoMessage;
import com.hzy.rabbitMQ.common.constant.QueueConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DirectReceiver {

    /**
     *  当转换器是 默认的 simpleMessageConverter 时的消息接收
     * @param msgString
     * @param contentType
     */
    @RabbitListener(queues = QueueConstant.DIRECT_QUEUE)
    public void onMessage01(String msgString, @Header(AmqpHeaders.CONTENT_TYPE) String contentType) {
        log.info("onMessage01 接收消息[{}], CONTENT_TYPE [{}]", msgString, contentType);
    }

    @RabbitListener(queues = QueueConstant.DIRECT_QUEUE)
    public void onMessage02(DemoMessage demoMessage) {
        log.info("onMessage02 接收消息[{}]", JSON.toJSONString(demoMessage));
    }

    @RabbitListener(queues = QueueConstant.DIRECT_QUEUE)
    public void onMessage03(Message<DemoMessage> demoMessage) {
        log.info("onMessage03 接收消息[{}]", JSON.toJSONString(demoMessage));
    }

    @RabbitListener(queues = QueueConstant.DIRECT_QUEUE)
    public void onMessage04(@Payload DemoMessage demoMessage) {
        log.info("onMessage04 接收消息[{}]", JSON.toJSONString(demoMessage));
    }

    @RabbitListener(queues = QueueConstant.DIRECT_QUEUE)
    public void onMessage05(@Payload DemoMessage demoMessage, @Header(AmqpHeaders.CONTENT_TYPE) String contentType) {
        log.info("onMessage05 接收消息[{}], CONTENT_TYPE[{}]", JSON.toJSONString(demoMessage), contentType);
    }

}
