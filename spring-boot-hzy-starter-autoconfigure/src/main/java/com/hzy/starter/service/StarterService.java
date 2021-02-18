package com.hzy.starter.service;

import com.hzy.starter.config.BeanProperty;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 注意此处不适用 组件注解 注入该类，而是
 * 通过自动配置类来控制组件的加载
 */
public class StarterService {

    @Autowired
    private BeanProperty beanProperty;

    public String doStarterService() {
        return "hello! " + beanProperty.getName() + " 当前的属性为" + beanProperty.getWords();
    }

}
