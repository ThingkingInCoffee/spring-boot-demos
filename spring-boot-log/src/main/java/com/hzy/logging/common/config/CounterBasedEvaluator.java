package com.hzy.logging.common.config;

import ch.qos.logback.core.boolex.EventEvaluator;
import ch.qos.logback.core.spi.ContextAwareBase;
import lombok.extern.slf4j.Slf4j;

/**
 * 实现了一个 evaluator，当第 1024 个日志事件到来时才会触发邮件发送
 */
@Slf4j
public class CounterBasedEvaluator extends ContextAwareBase implements EventEvaluator {

    static int LIMIT = 1024;
    int counter = 0;
    String name;

    public boolean evaluate(Object event) throws NullPointerException {
        counter++;
        if (counter == LIMIT) {
            counter = 0;
            return true;
        } else {
            return false;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void start() {
        log.info("调用自定义事件鉴定器 start 方法");
    }

    @Override
    public void stop() {
        log.info("调用自定义事件鉴定器 stop 方法");
    }

    @Override
    public boolean isStarted() {
        log.info("调用自定义事件鉴定器 isStarted 方法");
        return false;
    }
}