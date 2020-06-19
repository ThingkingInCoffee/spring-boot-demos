package com.hzy.datasource.common.config.mybatis.db01;

import com.hzy.datasource.common.config.datasource.db01.DataSourceConfigDB01;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = MybatisConfig01.MAPPER_SCAN_PACKAGES, sqlSessionTemplateRef = "db01SqlSessionTemplate")
public class MybatisConfig01 {

    public final static String MAPPER_SCAN_PACKAGES = "com.hzy.datasource.dao.db01";
    private final String MAPPER_LOCATIONS = "classpath:mapper/db01/*Mapper.xml";

    @Bean
    @Primary
    public SqlSessionFactory db01SqlSessionFactory(@Qualifier("db01DataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATIONS));
        return bean.getObject();
    }

    @Bean
    @Primary
    public SqlSessionTemplate db01SqlSessionTemplate(@Qualifier("db01SqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
