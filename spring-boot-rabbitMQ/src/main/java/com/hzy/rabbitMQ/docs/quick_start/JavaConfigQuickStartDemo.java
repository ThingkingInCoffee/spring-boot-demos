package com.hzy.rabbitMQ.docs.quick_start;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JavaConfigQuickStartDemo {

    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(JavaConfigDemo.class);
        AmqpTemplate template = context.getBean(AmqpTemplate.class);
        template.convertAndSend("myQueue", "foo");
        String foo = (String) template.receiveAndConvert("myQueue");
        System.out.println("receive message ==== " + foo);
    }

}
