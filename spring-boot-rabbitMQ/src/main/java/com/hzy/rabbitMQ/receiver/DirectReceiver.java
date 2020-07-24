package com.hzy.rabbitMQ.receiver;

import com.hzy.rabbitMQ.common.constant.QueueConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DirectReceiver {

    @RabbitListener(queues = QueueConstant.DIRECT_QUEUE)
    public void onMessage01(String msgString) {
        log.info("onMessage01 接收消息[{}]", msgString);
    }


}
