package com.hzy.logging;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 测试前，需要确认application.yml中使用的配置文件是logback-spring-filter.xml
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

    /**
     * 测试 matcher
     */
    @Test
    public void testMatcher() {
        log.debug("logging statement 0");
        log.info("logging statement 1");
        log.debug("logging statement 2");
        log.debug("logging statement 3");
        log.info("logging statement 4");
        log.debug("logging statement 5");
        log.debug("logging statement 6");
        log.info("logging statement 7");
        log.debug("logging statement 8");
        log.debug("logging statement 9");
    }

    /**
     * 测试 SampleMarker
     */
    @Test
    public void testSampleMarker() {
        log.debug("logging statement 0");
        log.info("sample");
        log.debug("logging statement 2");
        log.debug("sample");
        log.info("logging statement 4");
    }

    /**
     * 测试 DuplicateFilter
     */
    @Test
    public void testDuplicateFilter() {
        log.debug("logging statement {}", 0);
        log.debug("logging statement {}", 1); //第一次重复
        log.info("logging statement {}", 2);
        log.debug("logging statement {}", 3);
        log.debug("logging statement {}", 4);
        log.info("logging statement {}", 5);
        log.info("logging statement {}", 6);
        log.info("logging statement {}", 7);
        log.info("logging statement {}", 8);
        log.info("logging statement {}", 9);
        log.info("logging statement {}", 10);
        log.info("logging statement {}", 11);
        log.info("logging statement {}", 12);
    }


}
