package com.hzy.rabbitMQ.sender;

import com.hzy.rabbitMQ.bean.DemoMessage;
import com.hzy.rabbitMQ.common.constant.ExchangeConstant;
import com.hzy.rabbitMQ.common.constant.ResponseConstant;
import com.hzy.rabbitMQ.common.constant.RoutingKeyConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/sender")
public class SendController {

    @Autowired
    private DirectSenderService directSenderService;

    @GetMapping("/sendBatch")
    public String sendBatch() {
        for (int i = 0; i < 1; i++) {
            directSenderService.sendMessage(ExchangeConstant.DEMO_EXCHANGE, RoutingKeyConstant.DEMO_KEY, "DEMO-" + i);
        }
        return ResponseConstant.SUCCESS;
    }

    @GetMapping("/sendBatchJson")
    public String sendBatchJson() {
        for (int i = 0; i < 1; i++) {
            DemoMessage message = new DemoMessage();
            message.setAge(i);
            message.setAmount(new BigDecimal(i));
            message.setName("Demo-" + i);
            directSenderService.sendMessage(ExchangeConstant.DEMO_EXCHANGE, RoutingKeyConstant.DEMO_KEY, message);
        }
        return ResponseConstant.SUCCESS;
    }

}
