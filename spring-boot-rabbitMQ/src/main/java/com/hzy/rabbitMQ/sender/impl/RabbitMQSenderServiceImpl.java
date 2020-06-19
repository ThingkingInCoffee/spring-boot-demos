package com.hzy.rabbitMQ.sender.impl;

import com.hzy.rabbitMQ.bean.MsgBean;
import com.hzy.rabbitMQ.config.RabbitCustomConfig;
import com.hzy.rabbitMQ.sender.RabbitMQSenderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *  消息队列发送
 */
@Slf4j
@Service
public class RabbitMQSenderServiceImpl implements RabbitMQSenderService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(){
        MsgBean msgBean = new MsgBean();
        msgBean.setRoutingKey("test_key");
        msgBean.setTtl(60);
        log.info("===========开始发送数据==============");
        rabbitTemplate.convertAndSend("demo_exchange","demo_key", msgBean);
        log.info("===========发送数据结束==============");
    }

    /**
     * 发送消息
     * @param object
     */
    public void sendMessage(String ex, String key, Object object){
        log.info("===========开始发送数据==============");
        rabbitTemplate.convertAndSend(""+ex,""+key, object);
        log.info("===========发送数据结束==============");
    }

    /**
     * 发送消息
     * @param object
     */
    public void sendMessage(String ex, String key, Object object, CorrelationData correlationData){
        log.info("===========开始发送数据==============");
        rabbitTemplate.convertAndSend(""+ex,""+key, object, correlationData);
        log.info("===========发送数据结束==============");
    }


    /**
     * 测试私信队列 延时机制
     * @param routingKey
     * @return
     */
    public void sendDlxMessage(String routingKey){
        System.out.println("===========开始发送 DLX 数据=============routingKey ="+ routingKey+"  ========");
        rabbitTemplate.convertAndSend("test_dlx_exchange","dlx","policyNo1",message -> {
            MessageProperties messageProperties = message.getMessageProperties();
//            设置编码
            messageProperties.setContentEncoding("utf-8");
//            设置过期时间20*1000毫秒
            messageProperties.setExpiration("20000");
            return message;
        });
        log.info("===========发送数据结束==============");
    }

    /**
     * 发票请求 消息发送  路由key 和 合约Id/No 必填
     * @param msgBean
     * @return
     */
    public void sendInvoiceApplyMessage(MsgBean msgBean) {
        String ttl = msgBean.getTtl() != null ? Integer.toString(msgBean.getTtl()): RabbitCustomConfig.DEFAULT_DEMO_TTL;
        rabbitTemplate.convertAndSend(RabbitCustomConfig.DEMO_RECEIVE_EXCHANGE_NAME, msgBean.getRoutingKey(), msgBean,
            message -> {
                MessageProperties messageProperties = message.getMessageProperties();
                //设置编码
                messageProperties.setContentEncoding("utf-8");
                //设置过期时间默认120*1000毫秒 用于发票请求前等待延迟处理
                messageProperties.setExpiration(ttl);
                return message;
            }
        );
        log.info("===========发送数据结束==============");
    }

}
