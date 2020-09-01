package com.hzy.logging;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.util.ContextInitializer;
import ch.qos.logback.core.util.StatusPrinter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.slf4j.LoggerFactory;

@Slf4j
public class LogTest {

    @Test
    public void test01() {
        System.out.println("========初始无logback配置开始打印================");
        log.warn("warn log");
        log.error("error log");
        log.debug("debug log");
        log.trace("trace log");
        log.info("info log");
        System.out.println("========初始无logback配置结束打印================");

        // 打印日志处理的内部状态
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        StatusPrinter.print(loggerContext);
    }

    @Test
    public void test02() {
        Logger fooLogger = (Logger) LoggerFactory.getLogger("com.hzy.rabbitMQ");
        fooLogger.setLevel(Level.INFO);
        // 这条日志可以打印，因为 WARN >= INFO
        fooLogger.warn("父级警告信息");
        // 这条日志不会打印，因为 DEBUG < INFO
        fooLogger.debug("父级调试信息");

        // "com.hzy.rabbitMQ.logback" 会继承 "com.hzy.rabbitMQ" 的有效级别
        Logger Logger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger("com.hzy.rabbitMQ.logback");
        // 这条日志会打印，因为 INFO >= INFO
        Logger.info("子级信息");
        // 这条日志不会打印，因为 DEBUG < INFO
        Logger.debug("子级调试信息");
    }


    static {
        System.setProperty(ContextInitializer.CONFIG_FILE_PROPERTY, "special-logback.xml");
    }

    /**
     * 使用特定的logback
     */
    @Test
    public void test003() {
        Logger LOGGER = (Logger) LoggerFactory.getLogger(LogTest.class);
        LOGGER.debug("debug log");
        LOGGER.info("info log");
    }


}
