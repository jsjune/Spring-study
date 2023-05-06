package com.example.testcontainers;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class ContainerConfig {
    @Container
    static JdbcDatabaseContainer mySQLContainer = new MySQLContainer("mysql:5.7")
            .withDatabaseName("testDB")
            .withUsername("jsj")
            .withPassword("1234");
//            .withInitScript("db/schema.sql");
}
