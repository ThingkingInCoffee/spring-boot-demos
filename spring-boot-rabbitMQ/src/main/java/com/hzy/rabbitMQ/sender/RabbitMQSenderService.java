package com.hzy.rabbitMQ.sender;

import com.hzy.rabbitMQ.bean.MsgBean;
import org.springframework.amqp.rabbit.connection.CorrelationData;

public interface RabbitMQSenderService {

    void sendMessage();

    void sendDlxMessage(String routingKey);

    void sendInvoiceApplyMessage(MsgBean msgBean);

    void sendMessage(String ex, String key, Object object);

    void sendMessage(String ex, String key, Object object, CorrelationData correlationData);
}
