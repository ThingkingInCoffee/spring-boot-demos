package com.hzy.datasource.common.config.datasource.db02;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfigDB02 {

    private final String DATASOURCE_PROPERTIES_PREFIX = "spring.datasource.db02";

    @Bean
    @ConfigurationProperties(prefix = DATASOURCE_PROPERTIES_PREFIX)
    public DataSource db02DataSource() {
        //DruidDataSourceBuilder 需要 druid-spring-boot-starter 依赖
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    public DataSourceTransactionManager db02TransactionManager(@Qualifier("db02DataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
    
}
