package com.hzy.logging;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.util.ContextInitializer;
import org.slf4j.LoggerFactory;

public class Demo {

    static {
        System.setProperty(ContextInitializer.CONFIG_FILE_PROPERTY, "special-logback.xml");
    }

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(Demo.class);

    /**
     * 使用了指定的配置文件
     * special-logback.xml 配置文件中 configuration 的 debug 属性设置为 true，会打印logback状态信息
     * 低于配置文件级别的日志不被打印
     * @param args
     */
    public static void main(String[] args) {
        LOGGER.debug("debug log");
        LOGGER.info("info log");
        LOGGER.warn("warn log");
        LOGGER.error("error log");
    }


}
