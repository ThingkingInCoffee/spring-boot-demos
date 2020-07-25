package com.hzy.rabbitMQ.common.constant;

public interface RoutingKeyConstant {

    String DIRECT_ROUTING_KEY = "config_direct_routing_key";

    String TOPIC_ROUTING_KEY_A = "*.to";
    String TOPIC_ROUTING_KEY_B = "demo.*";
    String TOPIC_ROUTING_KEY_C = "test.to";

}
