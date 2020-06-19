package com.hzy.rabbitMQ.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitDirectConfig {

    @Bean
    public DirectExchange demoDirectExchange() {
        return new DirectExchange("demoDirectExchange", true, false);
    }

    @Bean
    public Queue demoDirectQueue() {
        return new Queue("demoDirectQueue",true,false, false,null);
    }

    @Bean
    public Binding demoDirectBinding() {
        //另一种绑定方式
        //return new Binding("demoDirectQueue", Binding.DestinationType.QUEUE,"demoDirectExchange","direct",null);
        return BindingBuilder.bind(demoDirectQueue()).to(demoDirectExchange()).with("direct");
    }


}
