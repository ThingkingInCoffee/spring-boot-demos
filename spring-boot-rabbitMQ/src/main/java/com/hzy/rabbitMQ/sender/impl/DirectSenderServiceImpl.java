package com.hzy.rabbitMQ.sender.impl;

import com.hzy.rabbitMQ.sender.DirectSenderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DirectSenderServiceImpl implements DirectSenderService {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    @Override
    public void sendMessage(Object object) {
        rabbitTemplate.convertAndSend(object);
    }

    /**
     * 使用 template 配置的 exchange
     * CorrelationData  为空
     * @param routingKey
     * @param object
     */
    @Override
    public void sendMessage(String routingKey, Object object) {
        rabbitTemplate.convertAndSend(routingKey, object);
    }

    /**
     *  CorrelationData  为空
     * @param exchange
     * @param routingKey
     * @param object
     */
    @Override
    public void sendMessage(String exchange, String routingKey, Object object) {
        rabbitTemplate.convertAndSend(exchange, routingKey, object);
    }

    /**
     *  使用 template 配置的 exchange 和 routingKey
     *  CorrelationData 为 null
     *  使用 自定义 messagePostProcessor
     * @param message
     * @param messagePostProcessor
     */
    @Override
    public void sendMessage(Object message, MessagePostProcessor messagePostProcessor) {
        rabbitTemplate.convertAndSend(message, messagePostProcessor);
    }

    /**
     *  使用 template 配置的 exchange
     *  指定 CorrelationData
     * @param routingKey
     * @param object
     * @param correlationData
     */
    @Override
    public void sendMessage(String routingKey, Object object, CorrelationData correlationData) {
        rabbitTemplate.convertAndSend(routingKey, object, correlationData);
    }

    /**
     *  使用 template 配置的 exchange
     *  指定 MessagePostProcessor
     *  CorrelationData 为空
     * @param routingKey
     * @param message
     * @param messagePostProcessor
     */
    @Override
    public void sendMessage(String routingKey, Object message, MessagePostProcessor messagePostProcessor) {
        rabbitTemplate.convertAndSend(routingKey, messagePostProcessor, messagePostProcessor);
    }

    @Override
    public void sendMessage(String exchange, String routingKey, Object object, CorrelationData correlationData) {
        rabbitTemplate.convertAndSend(exchange, routingKey, object, correlationData);
    }

    @Override
    public void sendMessage(Object message, MessagePostProcessor messagePostProcessor, CorrelationData correlationData) {
        rabbitTemplate.convertAndSend(message, messagePostProcessor, correlationData);
    }

    @Override
    public void sendMessage(String exchange, String routingKey, Object message, MessagePostProcessor messagePostProcessor) {
        rabbitTemplate.convertAndSend(exchange, routingKey, message, messagePostProcessor);
    }

    @Override
    public void sendMessage(String routingKey, Object message, MessagePostProcessor messagePostProcessor, CorrelationData correlationData) {
        rabbitTemplate.convertAndSend(routingKey, message, messagePostProcessor, correlationData);
    }

    @Override
    public void sendMessage(String exchange, String routingKey, Object message, MessagePostProcessor messagePostProcessor, CorrelationData correlationData) {
        rabbitTemplate.convertAndSend(exchange, routingKey, message, messagePostProcessor, correlationData);
    }
}
