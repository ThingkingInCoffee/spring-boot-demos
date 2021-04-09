package com.hzy.mybatis.test;

import com.alibaba.fastjson.JSON;
import com.hzy.mybatis.entry.DemoEntry;
import com.hzy.mybatis.entry.SensitiveEntry;
import com.hzy.mybatis.mapper.DemoEntryMapper;
import com.hzy.mybatis.mapper.SensitiveEntryMapper;
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
        SqlSessionFactory sqlSessionFactory = getFactoryWithXml();
//        SqlSessionFactory sqlSessionFactory = getFactoryWithoutXml();
        SqlSession sqlSession = sqlSessionFactory.openSession();
//        DemoEntry demoEntry = sqlSession.selectOne("com.hzy.mybatis.mapper.DemoEntryMapper.selectByPrimaryKey", 1);
        DemoEntryMapper mapper = sqlSession.getMapper(DemoEntryMapper.class);
        DemoEntry demoEntry = mapper.selectByPrimaryKey(1);
        System.out.println(JSON.toJSONString(demoEntry));
        System.out.println("=============================");
//        SensitiveEntryMapper sensitiveEntryMapper = sqlSession.getMapper(SensitiveEntryMapper.class);
//        SensitiveEntry entry = new SensitiveEntry();
//        entry.setAddress("ssss");
//        entry.setIdCard("pppp");
//        entry.setName("ttttttt");
//        entry.setPhone("13333333ss");
//        entry.setCreateTime(new Date());
//        sensitiveEntryMapper.insertSelective(entry);
//        sqlSession.commit();
//        SensitiveEntry sensitiveEntry = sensitiveEntryMapper.selectByPrimaryKey(entry.getId());
//        System.out.println(JSON.toJSONString(sensitiveEntry));
    }

    /**
     * xml 方式解析需要 mapper 映射到 xml， mapper 中的方法不能添加注解，会产生冲突异常
     * @return
     */
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

    /**
     * 注解方式 需要在 mapper 方法上添加 对应注解的sql语句用于映射
     * @return
     */
    public static SqlSessionFactory getFactoryWithoutXml() {
        DataSource dataSource = new UnpooledDataSource(driver, url, "root", "root");
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);
        configuration.addMapper(DemoEntryMapper.class);
//        configuration.addMappers("com.hzy.mybatis.mapper");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        return sqlSessionFactory;
    }

    public static SqlSessionFactory getFactoryWithJavaConfig() {
        DataSource dataSource = new UnpooledDataSource(driver, url, "root", "root");
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);
        configuration.setLazyLoadingEnabled(true);
//        configuration.setEnhancementEnabled(true);
        configuration.getTypeAliasRegistry().registerAlias(DemoEntry.class);
        configuration.getTypeAliasRegistry().registerAlias(SensitiveEntry.class);
        configuration.addMapper(DemoEntryMapper.class);
        configuration.addMapper(SensitiveEntryMapper.class);

        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(configuration);
        return factory;
    }

}
