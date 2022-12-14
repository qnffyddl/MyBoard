package com.example.board_0914.configuration;


import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;


@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = {"com.example.board_0914.mapper"})
public class DataSourceConfig {

  @Bean
  @ConfigurationProperties(prefix = "spring.datasource.hikari")
  public DataSource dataSource() {
    return DataSourceBuilder.create().type(HikariDataSource.class).build();
  }

  @Autowired
  ApplicationContext applicationContext;

  @Bean
  public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception{
    SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();

    sessionFactoryBean.setDataSource(dataSource);
    sessionFactoryBean.setTypeAliasesPackage("com.example.board_0914.dto");
    sessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:mapper/mybatis-config.xml"));
    sessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:mapper/**/*Mapper.xml"));

    return sessionFactoryBean.getObject();
  }


  @Bean
  public SqlSessionTemplate baseSqlSessionTemplate(SqlSessionFactory baseSqlSessionFactory) throws Exception {
    final SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(baseSqlSessionFactory);
    return sqlSessionTemplate;
  }

  @Bean
  public PlatformTransactionManager transactionManager() throws Exception {
    return new DataSourceTransactionManager(dataSource());
  }
}
