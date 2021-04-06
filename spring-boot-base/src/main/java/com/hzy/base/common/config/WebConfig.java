package com.hzy.base.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@Configuration
public class WebConfig {

//    springboot自动配置页面提交rest风格请求的put、delete转换
//    @Bean
//    @ConditionalOnMissingBean(HiddenHttpMethodFilter.class)
//    @ConditionalOnProperty(prefix = "spring.mvc.hiddenmethod.filter", name = "enabled", matchIfMissing = false)
//    public OrderedHiddenHttpMethodFilter hiddenHttpMethodFilter() {
//        return new OrderedHiddenHttpMethodFilter();
//    }
    @Bean
    public HiddenHttpMethodFilter getCustomHiddenFilter(){
        HiddenHttpMethodFilter filter = new HiddenHttpMethodFilter();
        filter.setMethodParam("_myMethod");  // 设置自定义的隐藏参数名称
        return filter;
    }


}
