package com.hzy.mybatis.test;

import com.hzy.mybatis.entry.DemoEntry;
import com.hzy.mybatis.mapper.DemoEntryMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Slf4j
public class SpringTest {

    /**
     * 使用spring获取mapper查询，该方式会每次都创建一个新的会话，从而产生“缓存失效”的现象场景
     */
    @DisplayName("使用spring获取mapper查询")
    @Test
    public void testSpringSessionProxy() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        DemoEntryMapper demoEntryMapper = ctx.getBean(DemoEntryMapper.class);
        DemoEntry demoEntry1 = demoEntryMapper.selectByPrimaryKey(1); // 两个语句每次都创建一个新的会话session
        DemoEntry demoEntry2 = demoEntryMapper.selectByPrimaryKey(1);
        log.info("是否相等[{}]", demoEntry1 == demoEntry2);  // false
    }

    /**
     * 通过开启事务控制，解决“缓存失效”的情况
     */
    @DisplayName("解决spring获取mapper查询缓存失效问题")
    @Test
    public void testSpringSessionProxyCacheResolve() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        DemoEntryMapper demoEntryMapper = ctx.getBean(DemoEntryMapper.class);
        DataSourceTransactionManager transactionManager = (DataSourceTransactionManager) ctx.getBean("transactionManager");
        transactionManager.getTransaction(new DefaultTransactionDefinition());
        DemoEntry demoEntry1 = demoEntryMapper.selectByPrimaryKey(1); // 两个语句每次都创建一个新的会话session
        DemoEntry demoEntry2 = demoEntryMapper.selectByPrimaryKey(1);
        log.info("是否相等[{}]", demoEntry1 == demoEntry2);  // true

        // mapper --> sqlSessionTemplate  -->  sqlSessionInterceptor  -->  sqlSessionFactory
    }

}
