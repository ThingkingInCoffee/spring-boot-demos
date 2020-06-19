package com.hzy.rabbitMQ.receiver;

import com.alibaba.fastjson.JSON;
import com.hzy.rabbitMQ.bean.MsgBean;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RabbitMqReceiver {
    
    /**
     * 处理发票发送邮件
     * 消费者确认：basicAck(long deliveryTag, boolean multiple)，其中deliveryTag 可以看作消息的编号，
     * 它是一个64 位的长整型值；multiple一般设为false，如果设为true则表示确认当前deliveryTag 编号及之前所有未被当前消费者确认的消息。
     * 消费者拒绝：basicNack(long deliveryTag, boolean multiple, boolean requeue)，其中deliveryTag 可以看作消息的编号，
     * 它是一个64 位的长整型值。multiple一般设为false，如果设为true则表示拒绝当前deliveryTag 编号及之前所有未被当前消费者确认的消息。
     * requeue参数表示是否重回队列，如果requeue 参数设置为true ，则RabbitMQ 会重新将这条消息存入队列尾部（注意是队列尾部），
     * 等待继续投递给订阅该队列的消费者，当然也可能是自己；如果requeue 参数设置为false ，则RabbitMQ立即会把消息从队列中移除，
     * 而不会把它发送给新的消费者。
     * 消息重发 - 接收端抛出异常
     * <p>
     * 死信队列及无路由队列配置
     *
     * @RabbitHandler
     * @RabbitListener(bindings = @QueueBinding(
     * value = @Queue(value = "invoice_message", arguments = {@Argument(name = "x-dead-letter-exchange", value = "exchange-dlx")}),
     * key = "invoice_message",
     * exchange = @Exchange(value = "invoice_exchange", arguments = {@Argument(name = "alternate-exchange", value = "exchange-unroute")})
     * ))
     */
    @RabbitHandler
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "demo_queue"),
            key = "demo_key",
            exchange = @Exchange(value = "demo_exchange")
    ))
    public void messageBusiness(MsgBean content, Channel channel, Message me) throws Exception {
        log.info("business message ====>>>{}", JSON.toJSONString(content));
        long deliveryTag = me.getMessageProperties().getDeliveryTag();
        //消息确认
        channel.basicAck(deliveryTag, false);
        log.info("messageBusiness 消息处理结束");
    }

}
