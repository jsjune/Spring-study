package com.example.testcontainers;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;

@Testcontainers
@Slf4j
class TestcontainersApplicationTests {
    @Container
    static MySQLContainer mySQLContainer = new MySQLContainer("mysql:5.7");

//    @BeforeEach
//    void beforeEach() {
//        mySQLContainer.start();
//    }
//
//    @AfterEach
//    void afterEach() {
//        mySQLContainer.stop();
//    }

    @Test
    void contextLoads() {
        System.out.println("=============================================");
        log.info("로그 getJdbcDriverInstance {} ", mySQLContainer.getJdbcDriverInstance());
        log.info("로그 getJdbcUrl {} ", mySQLContainer.getJdbcUrl());
        log.info("로그 getMappedPort {} ", mySQLContainer.getMappedPort(3306));
        log.info("로그 getHost {} ", mySQLContainer.getHost());
        log.info("로그 getUsername {} ", mySQLContainer.getUsername());
        log.info("로그 getPassword {} ", mySQLContainer.getPassword());
        System.out.println("=============================================");
    }

}
