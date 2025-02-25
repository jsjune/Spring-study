package com.example.springmysqlsharding.config;

import com.example.springmysqlsharding.utils.SnowflakeIdGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public SnowflakeIdGenerator snowflakeIdGenerator(@Value("${snowflake.worker-id}") long workerId) {
        return new SnowflakeIdGenerator(workerId);
    }

}
