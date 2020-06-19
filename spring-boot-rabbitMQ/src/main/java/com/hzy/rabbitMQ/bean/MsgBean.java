package com.hzy.rabbitMQ.bean;

import lombok.Data;

@Data
public class MsgBean {
    private String routingKey;
    private Integer ttl;
    private String context;

    public MsgBean() {
    }

    public MsgBean(String routingKey, String context) {
        this.routingKey = routingKey;
        this.context = context;
    }

    public MsgBean(String routingKey, Integer ttl, String context) {
        this.routingKey = routingKey;
        this.ttl = ttl;
        this.context = context;
    }
}