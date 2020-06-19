package com.hzy.datasource.common.config.mybatis.db02;


import com.hzy.datasource.common.config.datasource.db02.DataSourceConfigDB02;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = MybatisConfig02.MAPPER_SCAN_PACKAGES, sqlSessionTemplateRef = "db02SqlSessionTemplate")
public class MybatisConfig02 {

    public final static String MAPPER_SCAN_PACKAGES = "com.hzy.datasource.dao.db02";
    private final String MAPPER_LOCATIONS = "classpath:mapper/db02/*Mapper.xml";

    @Bean
    public SqlSessionFactory db02SqlSessionFactory(@Qualifier("db02DataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATIONS));
        return bean.getObject();
    }

    @Bean
    public SqlSessionTemplate db02SqlSessionTemplate(@Qualifier("db02SqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
