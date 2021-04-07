package com.hzy.mybatis.test;

import com.alibaba.fastjson.JSON;
import com.hzy.mybatis.entry.DemoEntry;
import com.hzy.mybatis.mapper.DemoEntryMapper;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;

public class SqlSessionFactoryDemo {

    private static final String url = "jdbc:mysql://localhost:3306/demo?useSSL=false&useUnicode=true&characterEncoding=utf8&allowMultiQueries=true&serverTimezone=Asia/Shanghai";
    private static final String driver = "com.mysql.cj.jdbc.Driver";


    public static void main(String[] args) {
//        SqlSessionFactory sqlSessionFactory = getFactoryWithXml();
        SqlSessionFactory sqlSessionFactory = getFactoryWithoutXml();
        SqlSession sqlSession = sqlSessionFactory.openSession();
//        Object demoEntry = sqlSession.selectOne("com.hzy.mybatis.mapper.DemoEntryMapper.selectByPrimaryKey", 1);
        DemoEntryMapper mapper = sqlSession.getMapper(DemoEntryMapper.class);
        DemoEntry demoEntry = mapper.selectByPrimaryKey(1);
        System.out.println(JSON.toJSONString(demoEntry));
    }

    public static SqlSessionFactory getFactoryWithXml() {
        String resource = "config/mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        return sqlSessionFactory;
    }

    public static SqlSessionFactory getFactoryWithoutXml() {
        DataSource dataSource = new UnpooledDataSource(driver, url, "root", "root");
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);
        configuration.addMappers("com.hzy.mybatis.mapper.DemoEntryMapper");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        return sqlSessionFactory;
    }

}
