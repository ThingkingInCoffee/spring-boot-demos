package com.hzy.base.controller;

import com.hzy.base.config.configurations.ConfigDemoBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/configs")
@RestController
public class ConfigFileDemo {

    @Autowired
    private ConfigDemoBean configDemoBean;

    @GetMapping("testConfigDir")
    public String testConfigDir(){
        return configDemoBean.toString();
    }


}
