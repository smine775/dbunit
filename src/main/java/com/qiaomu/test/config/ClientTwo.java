package com.qiaomu.test.config;

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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * qiaomu
 */
@Configuration
@MapperScan(basePackages = "com.qiaomu.test.mapper.two", sqlSessionTemplateRef  = "slaveofSqlSessionTemplate")
public class ClientTwo {

    @Bean(name = "slaveofDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.two")
    public DataSource testDataSource() {
        return DataSourceBuilder.create().create().type(com.alibaba.druid.pool.DruidDataSource.class).build();
    }
    @Bean(name = "slaveofSqlSessionFactory")
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("slaveofDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/two/*.xml"));
        return bean.getObject();
    }
    @Bean(name = "slaveofTransactionManager")
    public DataSourceTransactionManager testTransactionManager(@Qualifier("slaveofDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
    @Bean(name = "slaveofSqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("slaveofSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
    @Bean(name = "h2TemplateTwo")
    @Primary
    public JdbcTemplate h2Template(@Qualifier("slaveofDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}