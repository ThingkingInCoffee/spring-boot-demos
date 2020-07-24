package com.hzy.rabbitMQ.exchange.direct;

import com.hzy.rabbitMQ.common.constant.ExchangeConstant;
import com.hzy.rabbitMQ.common.constant.QueueConstant;
import com.hzy.rabbitMQ.common.constant.RoutingKeyConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class DirectConfig {

    @Bean
    public DirectExchange directDemoExchange() {
        return new DirectExchange(ExchangeConstant.DIRECT_EXCHANGE, true, false);
    }

    @Bean
    public Queue directDemoQueue() {
        return new Queue(QueueConstant.DIRECT_QUEUE);
    }

    @Bean
    public Binding directDemoBinding() {
        return BindingBuilder.bind(directDemoQueue()).to(directDemoExchange()).with(RoutingKeyConstant.DIRECT_ROUTING_KEY);
    }

}
