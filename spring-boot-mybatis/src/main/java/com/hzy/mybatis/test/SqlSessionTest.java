package com.hzy.mybatis.test;

import com.alibaba.fastjson.JSON;
import com.hzy.mybatis.common.constant.DatabaseConstant;
import com.hzy.mybatis.entry.DemoEntry;
import com.hzy.mybatis.mapper.DemoEntryMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.BatchExecutor;
import org.apache.ibatis.executor.ReuseExecutor;
import org.apache.ibatis.executor.SimpleExecutor;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.*;
import org.apache.ibatis.transaction.jdbc.JdbcTransaction;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class SqlSessionTest {

    private static SqlSessionFactory sqlSessionFactory;
    private static SqlSession sqlSession;
    private static Configuration configuration;
    private static Connection connection;
    private static JdbcTransaction jdbcTransaction;

    @DisplayName("获取session工厂和配置")
    @BeforeAll  // 替换 junit4 中的 before
    public static void getSqlSessionFactory() {
        String resource = "config/mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        configuration = sqlSessionFactory.getConfiguration();
        sqlSession = sqlSessionFactory.openSession();
        log.info("初始化 sqlSessionFactory BeforeAll");
    }

    @DisplayName("获取jdbc连接和事务")
    @BeforeAll
    public static void getJdbcConnectionAndTx() throws SQLException {
        connection = DriverManager.getConnection(DatabaseConstant.url, DatabaseConstant.username, DatabaseConstant.password);
        jdbcTransaction = new JdbcTransaction(connection);
    }

    @DisplayName("测试mapper")
    @Test
    public void testMapper() {
        DemoEntryMapper mapper = sqlSession.getMapper(DemoEntryMapper.class);
        DemoEntry demoEntry = mapper.selectByPrimaryKey(1);
        log.info("openSession 查询结果[{}]", JSON.toJSONString(demoEntry));
        DemoEntry objects = sqlSession.selectOne("com.hzy.mybatis.mapper.DemoEntryMapper.selectByPrimaryKey", 1);
        log.info("openSession 查询结果[{}]", JSON.toJSONString(objects));
        // 二者使用了同一个sqlSession的同一个statement进行查询，默认使用了一级缓存
        log.info("查询结果相等？ [{}]", demoEntry == objects);
    }

    @DisplayName("测试 SIMPLE executor")
    @Test
    public void testSimpleExecutorType() throws SQLException {
        SimpleExecutor executor = new SimpleExecutor(configuration, jdbcTransaction);
        MappedStatement mappedStatement = configuration.getMappedStatement("com.hzy.mybatis.mapper.DemoEntryMapper.selectByPrimaryKey");
        List<Object> query1 = executor.doQuery(mappedStatement, 1, new RowBounds()
                , SimpleExecutor.NO_RESULT_HANDLER, mappedStatement.getBoundSql(1));
        List<Object> query2 = executor.doQuery(mappedStatement, 1, new RowBounds()
                , SimpleExecutor.NO_RESULT_HANDLER, mappedStatement.getBoundSql(1));
        log.info("testSimpleExecutorType [{}]", query1 == query2); // FALSE
    }

    @DisplayName("测试 REUSE executor")
    @Test
    public void testReuseExecutorType() throws SQLException {
        ReuseExecutor executor = new ReuseExecutor(configuration, jdbcTransaction);
        MappedStatement mappedStatement = configuration.getMappedStatement("com.hzy.mybatis.mapper.DemoEntryMapper.selectByPrimaryKey");
        List<Object> query1 = executor.doQuery(mappedStatement, 1, new RowBounds()
                , ReuseExecutor.NO_RESULT_HANDLER, mappedStatement.getBoundSql(1));
        List<Object> query2 = executor.doQuery(mappedStatement, 1, new RowBounds()
                , ReuseExecutor.NO_RESULT_HANDLER, mappedStatement.getBoundSql(1));
        log.info("testReuseExecutorType [{}]", query1 == query2); //FALSE
    }

    @DisplayName("测试 BATCH executor")
    @Test
    public void testBatchExecutorType() throws SQLException {
        // 仅适用于增删改，不适用查询
        BatchExecutor executor = new BatchExecutor(configuration, jdbcTransaction);
        MappedStatement mappedStatement = configuration.getMappedStatement("com.hzy.mybatis.mapper.DemoEntryMapper.insertDemo");
        DemoEntry entry1 = new DemoEntry();
        entry1.setDemoName("testExecutor1");
        entry1.setDemoType(1);
        entry1.setZipCode("100001");
        executor.doUpdate(mappedStatement, entry1);
        DemoEntry entry2 = new DemoEntry();
        entry2.setDemoName("testExecutor2");
        entry2.setDemoType(2);
        entry2.setZipCode("100002");
        executor.doUpdate(mappedStatement, entry2);
        MappedStatement updateStatement = configuration.getMappedStatement("com.hzy.mybatis.mapper.DemoEntryMapper.updateDemo");
        Map<String, String> map1 = new HashMap<>();
        map1.put("arg0","1");
        map1.put("arg1", "testUpdate1");
        executor.doUpdate(updateStatement, map1);
        Map<String, String> map2 = new HashMap<>();
        map2.put("arg0","2");
        map2.put("arg1", "testUpdate2");
        executor.doUpdate(updateStatement, map2);
        executor.doFlushStatements(false); // doUpdate语句需要使用手动flush 才会提交更新
    }


}
