package com.hzy.rabbitMQ.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitCustomConfig {

    public static final String DEFAULT_DEMO_TTL = "120000";  //两分钟
    //死信队列 交换机标识符
    public static final String DEAD_LETTER_EXCHANGE_PARAM = "x-dead-letter-exchange";
    //死信队列交换机绑定键标识符
    public static final String DEAD_LETTER_ROUTING_KEY_PARAM = "x-dead-letter-routing-key";

    public static final String DEMO_RECEIVE_EXCHANGE_NAME = "test_dlx_exchange";

    public static final String DEMO_RECEIVE_QUEUE_NAME = "test_dlx_queue";

    public static final String DEMO_RECEIVE_ROUTING_KEY = "dlx";

    public static final String DEMO_DEAL_EXCHANGE_NAME = "demo_exchange";

    public static final String DEMO_DEAL_ROUTING_KEY = "demo_key";


    /**
     * 配置json格式消息序列化器
     * @return
     */
    @Bean
    public MessageConverter messageConverter() {
        Jackson2JsonMessageConverter jsonMessageConverter = new Jackson2JsonMessageConverter();
        // 消息转换时，指定转换的依据 默认是推测 INFERRED，可以设置 TYPE_ID 通过 header 的 _TYPE_ID_值
        jsonMessageConverter.setTypePrecedence(Jackson2JavaTypeMapper.TypePrecedence.TYPE_ID);
        return jsonMessageConverter;
    }

    /**
     * 配置demo接收交换机
     * @return
     */
    @Bean
    DirectExchange demoReceiveExchange() {
        return new DirectExchange(DEMO_RECEIVE_EXCHANGE_NAME, true, false);
    }

    /**
     * 定义demo接收队列 配置其死信队列参数 消息超时之后到达新队列后被监听处理
     * @return
     */
    @Bean
    public Queue demoReceiveQueue() {
        Map<String, Object> args = new HashMap<>(2);
        args.put(DEAD_LETTER_EXCHANGE_PARAM, DEMO_DEAL_EXCHANGE_NAME);
        args.put(DEAD_LETTER_ROUTING_KEY_PARAM, DEMO_DEAL_ROUTING_KEY);
        Queue queue = new Queue(DEMO_RECEIVE_QUEUE_NAME, true, false, false, args);
        return queue;
    }

    /**
     * 绑定demo交换机和队列
     * @return
     */
    @Bean
    public Binding invoiceReceiveBinding() {
        return BindingBuilder.bind(demoReceiveQueue()).to(demoReceiveExchange()).with(DEMO_RECEIVE_ROUTING_KEY);
    }

}
