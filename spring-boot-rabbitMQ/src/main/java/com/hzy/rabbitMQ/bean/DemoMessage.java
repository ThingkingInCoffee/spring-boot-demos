package com.hzy.rabbitMQ.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DemoMessage {

    private String name;
    private Integer age;
    private BigDecimal amount;

}
