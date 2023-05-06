package com.example.testcontainers;

import lombok.extern.slf4j.Slf4j;
import org.junit.ClassRule;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@SpringBootTest
@Slf4j
//public class HumanServiceTest extends ContainerConfig{
public class HumanServiceTest {
//    @Container
//    MySQLContainer mySQLContainer = new MySQLContainer("mysql:5.7");

    @Autowired
    private HumanService humanService;

    @Autowired
    private HumanRepository humanRepository;

//    @BeforeEach
//    void before() {
//        mySQLContainer.start();
//    }
//
//    @AfterEach
//    void after() {
//        mySQLContainer.stop();
//    }

    @Test
    void test() {
//        log.info("로그 JdbcUrl {} ", mySQLContainer.getJdbcUrl());
        Human findHuman = humanService.save();
        assertThat(findHuman.getUsername()).isEqualTo("aaa");
        assertThat(findHuman.getAge()).isEqualTo(20);
    }

    @Test
    void test2() {
//        log.info("로그 JdbcUrl {} ", mySQLContainer.getJdbcUrl());
        Human findHuman = humanService.save();
        assertThat(findHuman.getUsername()).isEqualTo("aaa");
        assertThat(findHuman.getAge()).isEqualTo(20);
    }
    
}
