package com.hzy.rabbitMQ.sender;

import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.connection.CorrelationData;

public interface DirectSenderService {

    void sendMessage(Object object);

    void sendMessage(String routingKey, Object object);

    void sendMessage(String exchange, String routingKey, Object object);

    void sendMessage(Object message, MessagePostProcessor messagePostProcessor);

    void sendMessage(String routingKey, Object object, CorrelationData correlationData);

    void sendMessage(String routingKey, Object message, MessagePostProcessor messagePostProcessor);

    void sendMessage(String exchange, String routingKey, Object object, CorrelationData correlationData);

    void sendMessage(Object message, MessagePostProcessor messagePostProcessor, CorrelationData correlationData);

    void sendMessage(String exchange, String routingKey, Object message, MessagePostProcessor messagePostProcessor);

    void sendMessage(String routingKey, Object message, MessagePostProcessor messagePostProcessor, CorrelationData correlationData);

    void sendMessage(String exchange, String routingKey, Object message, MessagePostProcessor messagePostProcessor, CorrelationData correlationData);

}
