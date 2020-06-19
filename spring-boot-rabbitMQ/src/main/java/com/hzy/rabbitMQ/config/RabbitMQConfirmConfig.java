package com.hzy.rabbitMQ.config;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class RabbitMQConfirmConfig implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {

    private final Logger logger = LoggerFactory.getLogger(RabbitMQConfirmConfig.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 配置 rabbit 的 发送确认
     */
    @PostConstruct
    public void send(){
        this.rabbitTemplate.setReturnCallback(this);
        this.rabbitTemplate.setConfirmCallback(this);
        this.rabbitTemplate.setMandatory(true);
    }

    /**
     * Returned message callback.交换机到队列 确认
     * @param message the returned message.
     * @param replyCode the reply code.
     * @param replyText the reply text.
     * @param exchange the exchange.
     * @param routingKey the routing key.
     */
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText,
                                String exchange, String routingKey){
        logger.info("replyCode:{},replyText:{}",replyCode,replyText);
        logger.info("消息从交换机:{} ===> 路由:{}发送到队列失败,消息内容:{}",exchange,routingKey,JSONObject.toJSONString(message));
    }

    /**
     *  消息到达交换机确认
     * @param correlationData
     * @param ack
     * @param cause
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        String msgId = null;
        if(correlationData != null){
            msgId = correlationData.getId();
        }
        if (!ack) {
            logger.info("消息 id {} 发送到交换机失败,原因:{}", msgId, cause);
        } else {
            logger.info("客户端已确认,消息 id {} 发送到队列成功", msgId);
        }
    }
}
