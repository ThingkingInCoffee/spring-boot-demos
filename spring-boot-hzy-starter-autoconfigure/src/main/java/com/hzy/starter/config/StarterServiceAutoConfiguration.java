package com.hzy.starter.config;


import com.hzy.starter.service.StarterService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 标明是一个配置类
@EnableConfigurationProperties(BeanProperty.class)  // 开启属性绑定，将属性BeanProperty绑定进来放到容器中
public class StarterServiceAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(StarterService.class) //当容器中不存在时才往容器中添加
    public StarterService starterService(){
        StarterService starterService = new StarterService();
        return starterService;
    }


}
