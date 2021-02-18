package com.hzy.base.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 属性配置绑定功能
 * 组合一： @Component 和 @ConfigurationProperties(prefix = "custom")
 *          将属性注册为组件，并绑定配置，常用于自己定义的一些配置
 * 组合二： @Configuration 和 @EnableConfigurationProperties(ConfigurationPropertiesBean.class) 和 @ConfigurationProperties(prefix = "custom")
 *          在配置类上开启属性配置，在目标类开启前缀匹配，则可以不使用 @Component
 */

//@Component
@ConfigurationProperties(prefix = "custom")
public class ConfigurationPropertiesBean {

    private String brand;

    private Integer price;

    @Override
    public String toString() {
        return "ConfigurationPropertiesBeanConfig{" +
                "brand='" + brand + '\'' +
                ", price=" + price +
                '}';
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
