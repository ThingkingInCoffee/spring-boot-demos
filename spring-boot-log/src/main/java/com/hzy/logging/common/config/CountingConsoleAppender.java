package com.hzy.logging.common.config;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import ch.qos.logback.core.Layout;

/**
 * 自定义的控制台 Appender
 * 设置项控制多台输出限定数量的事件信息,当达到限定数量之后,会自动关闭
 */
public class CountingConsoleAppender extends AppenderBase<ILoggingEvent> {

    static int DEFAULT_LIMIT = 10;
    int counter = 0;
    int limit = DEFAULT_LIMIT;

    Layout<ILoggingEvent> layout;

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getLimit() {
        return limit;
    }

    /**
     * 在 logback 配置期间，start() 方法会被自动调用，用来验证 appender 的各种属性是否设置与一致
     */
    @Override
    public void start() {
        if (this.layout == null) {
            addError("No layout set for the appender named [" + name + "].");
            return;
        }

        String header = layout.getFileHeader();
        System.out.print(header);
        super.start();
    }

    /**
     * AppenderBase.doAppend() 方法会调用它子类的所有 append() 方法
     * @param event
     */
    public void append(ILoggingEvent event) {
        if (counter >= limit) {
            return;
        }
        // output the events as formatted by patternLayout
        String eventStr = this.layout.doLayout(event);

        System.out.print(eventStr);

        // prepare for next event
        counter++;
    }

    public Layout<ILoggingEvent> getLayout() {
        return layout;
    }

    public void setLayout(Layout<ILoggingEvent> layout) {
        this.layout = layout;
    }
}