package com.hzy.base;

import com.hzy.TestScanPackage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages = "com.hzy")
public class BootBaseApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(BootBaseApplication.class, args);
//        Object testScanPackage = run.getBean(TestScanPackage.class);
//        System.out.println(testScanPackage);
        String[] beanDefinitionNames = run.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
    }

}
