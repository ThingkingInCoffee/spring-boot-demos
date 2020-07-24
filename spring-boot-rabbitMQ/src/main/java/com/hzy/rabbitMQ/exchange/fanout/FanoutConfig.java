package com.hzy.rabbitMQ.exchange.fanout;

import com.hzy.rabbitMQ.common.constant.ExchangeConstant;
import com.hzy.rabbitMQ.common.constant.QueueConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class FanoutConfig {

    @Bean
    public FanoutExchange fanoutDemoExchange() {
        return new FanoutExchange(ExchangeConstant.FANOUT_EXCHANGE, true, false);
    }

    @Bean
    public Queue fanoutDemoQueueA() {
        return new Queue(QueueConstant.FANOUT_QUEUE_A, true);
    }

    @Bean
    public Queue fanoutDemoQueueB() {
        return new Queue(QueueConstant.FANOUT_QUEUE_B, true, false, false);
    }

    @Bean
    public Queue fanoutDemoQueueC() {
        return new Queue(QueueConstant.FANOUT_QUEUE_C);
    }

    @Bean
    public Binding fanoutBindingA() {
        return BindingBuilder.bind(fanoutDemoQueueA()).to(fanoutDemoExchange());
    }

    @Bean
    public Binding fanoutBindingB() {
        return BindingBuilder.bind(fanoutDemoQueueB()).to(fanoutDemoExchange());
    }

    @Bean
    public Binding fanoutBindingC() {
        return BindingBuilder.bind(fanoutDemoQueueC()).to(fanoutDemoExchange());
    }

}
