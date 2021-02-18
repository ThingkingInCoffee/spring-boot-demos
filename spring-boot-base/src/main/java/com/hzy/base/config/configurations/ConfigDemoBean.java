package com.hzy.base.config.configurations;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Data
@Profile(value = {"default","test"})  //当默认环境 和 test 环境下 加载该bean
@Component
@ConfigurationProperties(prefix = "cfg")
public class ConfigDemoBean {

    private String name;

    private String fileUrl;
}
