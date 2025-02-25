package com.example.springmysqlsharding.config;

import javax.sql.DataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig {

    @Bean(name = "shard1DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.shard1")
    public DataSource shard1DataSource() {
        return new DriverManagerDataSource();
    }

    @Bean(name = "shard2DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.shard2")
    public DataSource shard2DataSource() {
        return new DriverManagerDataSource();
    }

    @Bean(name = "shard3DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.shard3")
    public DataSource shard3DataSource() {
        return new DriverManagerDataSource();
    }

    @Bean(name = "routingDataSource")
    @Primary
    public DataSource routingDataSource() {
        ShardRoutingDataSource routingDataSource = new ShardRoutingDataSource();
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put("shard1", shard1DataSource());
        targetDataSources.put("shard2", shard2DataSource());
        targetDataSources.put("shard3", shard3DataSource());

        routingDataSource.setTargetDataSources(targetDataSources);
        routingDataSource.setDefaultTargetDataSource(shard1DataSource()); // 기본 데이터소스 설정
        return routingDataSource;
    }
}

