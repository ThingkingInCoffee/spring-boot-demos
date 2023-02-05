package com.hzy.websocket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * 注入一个ServerEndpointExporter
 * 用于自动发现并注册使用注解@ServerEndpoint声明的WebSocket Endpoint
 * 如果使用非springboot内置容器以外的的独立的servlet容器，则不需要注入exporter，由容器自己提供并管理
 */
@Configuration
public class WebSocketConfig {

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

}
