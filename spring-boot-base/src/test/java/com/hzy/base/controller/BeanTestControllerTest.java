package com.hzy.base.controller;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("测试displayName类注解")
//@SpringBootTest  // 整合spring boot 进行单元测试，如不添加无法拿到spring容器中的bean
class BeanTestControllerTest {

    @DisplayName("测试displayName方法注解")
    @Test
    public void test01 (){
        System.out.println(1);
    }

    @DisplayName("测试方法二")
    @Test
    public void test02 (){
        System.out.println(2);
    }

    // 禁用方法
    @Disabled
    @Test
    public void test03 (){
        System.out.println(3);
    }

    // 测试超时,测试方法执行时间超过指定时间抛出异常
    @Timeout(3)
    @Test
    public void test04 () throws InterruptedException {
        System.out.println(4);
        Thread.sleep(5000);
        System.out.println(44);
    }

    @BeforeEach
    public void testBeforeEach(){
        System.out.println("测试即将开始");
    }

    @AfterEach
    public void testAfterEach(){
        System.out.println("测试已经结束");
    }

    // 必须设置为静态方法
    @BeforeAll
    public static void testBeforeAll(){
        System.out.println("所有测试即将开始");
    }

    // 必须设置为静态方法
    @AfterAll
    public static void testAAfterAll(){
        System.out.println("所有测试已经结束");
    }

    @DisplayName("测试参数化测试")
    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5,6})
    public void testParameterized(int i){
        System.out.println(Thread.currentThread().getName());;
        System.out.println(i);
    }

    @DisplayName("测试方法参数化测试")
    @ParameterizedTest
    @MethodSource("testMethodSourceString")
    public void testMethodSource(String s){
        System.out.println(Thread.currentThread().getName());;
        System.out.println(s);
    }

    // 此方法必须为静态，且返回 stream
    public static Stream<String> testMethodSourceString(){
        return Stream.of("baidu","alibaba","google");
    }




}