package com.hzy.base.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * 用于引入 xml 配置文件重的 bean
 * 组合使用注解
 * 引入结果可以在 主方法中查看
 */
@Configuration
@ImportResource("classpath:beans.xml")
public class ImportResourceBeanConfig {


}
