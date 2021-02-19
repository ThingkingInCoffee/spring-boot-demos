package com.hzy.starter.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
//@Component  //此处不适用 注解注入，通过自动配置类中开启属性绑定注入容器
@ConfigurationProperties("starter")
public class BeanProperty {

    private String name = "admin";

    private String words = "默认原始属性";
}
