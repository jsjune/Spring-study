package com.example.testcontainers;

import org.junit.ClassRule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@SpringBootTest
public class HumanServiceTest {
    @Container
    static MySQLContainer mySQLContainer = new MySQLContainer("mysql:5.7");

//    @ClassRule
//    static DockerComposeContainer composeContainer =
//            new DockerComposeContainer(new File("src/test/resources/docker-compose.yml"))
//                    .withExposedService("study-db", 3307);

    @Autowired
    private HumanService humanService;

    @Autowired
    private HumanRepository humanRepository;

    @Test
    void test() {
        Human findHuman = humanService.save();

        Human human = humanRepository.save(new Human("aaa", 20));

        assertThat(human.getUsername()).isEqualTo(findHuman.getUsername());
    }
}
