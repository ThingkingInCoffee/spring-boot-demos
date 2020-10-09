package com.hzy.logging;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *  测试前，需要确认application.yml中使用的配置文件是logback-spring-filter.xml
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = LogbackApplication.class)
public class FilterTest {

    /**
     * 对应使用CONSOLE_LEVEL的appender
     */
    @Test
    public void testLevelFilter() {
        log.debug("test debug log");
        log.info("test info log");
        log.warn("test warn log");
        log.error("test error log");
        log.info("test jan1ino log");
        log.info("test janino log"); // janino 过滤器会过滤掉
    }




}
