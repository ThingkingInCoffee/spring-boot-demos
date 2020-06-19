package com.hzy.datasource.common.config.datasource.db01;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfigDB01 {

    private final String DATASOURCE_PROPERTIES_PREFIX = "spring.datasource.db01";

//    @Bean
//    @ConfigurationProperties(prefix = DATASOURCE_PROPERTIES_PREFIX)
//    public DataSource druidDataSource(){
//        return new DruidDataSource();
//    }

    @Bean
    @ConfigurationProperties(prefix = DATASOURCE_PROPERTIES_PREFIX)
    @Primary
    public DataSource db01DataSource() {
        DataSourceBuilder builder = DataSourceBuilder.create();
        builder.type(DruidDataSource.class);
        return builder.build();
//        return DataSourceBuilder.create().build();
//        return DataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public DataSourceTransactionManager db01TransactionManager(@Qualifier("db01DataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}
