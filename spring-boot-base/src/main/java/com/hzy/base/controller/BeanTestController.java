package com.hzy.base.controller;

import com.hzy.base.common.config.ConfigurationPropertiesBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping("/bean")
@RestController
public class BeanTestController {

    @Resource
    private ConfigurationPropertiesBean propertiesBeanConfig;

    @GetMapping("/getBean01")
    public String getConfigPropertiesBeans() {
        return propertiesBeanConfig.getBrand() + propertiesBeanConfig.getPrice();
    }

}
