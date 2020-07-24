package com.hzy.rabbitMQ.exchange.topic;

import com.hzy.rabbitMQ.common.constant.ExchangeConstant;
import com.hzy.rabbitMQ.common.constant.QueueConstant;
import com.hzy.rabbitMQ.common.constant.RoutingKeyConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class TopicConfig {

    @Bean
    public TopicExchange topicDemoExchange() {
        return new TopicExchange(ExchangeConstant.TOPIC_EXCHANGE, true, false);
    }

    @Bean
    public Queue topicDemoQueueA() {
        return new Queue(QueueConstant.TOPIC_QUEUE_A);
    }

    @Bean
    public Queue topicDemoQueueB() {
        return new Queue(QueueConstant.TOPIC_QUEUE_B);
    }

    @Bean
    public Queue topicDemoQueueC() {
        return new Queue(QueueConstant.TOPIC_QUEUE_C);
    }

    @Bean
    public Binding topicDemoBindingA() {
        return BindingBuilder.bind(topicDemoQueueA()).to(topicDemoExchange()).with(RoutingKeyConstant.TOPIC_ROUTING_KEY_A);
    }

    @Bean
    public Binding topicDemoBindingB() {
        return BindingBuilder.bind(topicDemoQueueA()).to(topicDemoExchange()).with(RoutingKeyConstant.TOPIC_ROUTING_KEY_B);
    }

    @Bean
    public Binding topicDemoBindingC() {
        return BindingBuilder.bind(topicDemoQueueA()).to(topicDemoExchange()).with(RoutingKeyConstant.TOPIC_ROUTING_KEY_C);
    }


}
