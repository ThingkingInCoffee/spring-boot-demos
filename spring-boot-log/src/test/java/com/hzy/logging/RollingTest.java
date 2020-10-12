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
public class RollingTest {

    /**
     * 测试 TimeBasedRolling
     */
    @Test
    public void testTimeBasedRolling() throws InterruptedException {

        for (int i = 0; i < 1000000; i++) {
            if (i % 10000 == 0) {
                System.out.println("========" + i);
                Thread.sleep(1000);
                log.info("testTimeBasedRolling get num {}", i);
            }
        }

    }

    /**
     * 测试 matcher
     */
    @Test
    public void testMatcher() {

    }

    /**
     * 测试 SampleMarker
     */
    @Test
    public void testSampleMarker() {

    }

    /**
     * 测试 DuplicateFilter
     */
    @Test
    public void testDuplicateFilter() {

    }


}
