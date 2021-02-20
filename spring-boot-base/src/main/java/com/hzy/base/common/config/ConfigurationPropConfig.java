package com.hzy.base.common.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

// 标识为配置类
@Configuration
// 开启属性绑定，将属性ConfigurationPropertiesBean绑定进来放到容器中
@EnableConfigurationProperties(ConfigurationPropertiesBean.class)
public class ConfigurationPropConfig {



}
