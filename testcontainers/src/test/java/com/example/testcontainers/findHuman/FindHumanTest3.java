package com.example.testcontainers.findHuman;

import com.example.testcontainers.ContainerConfig;
import com.example.testcontainers.Human;
import com.example.testcontainers.HumanRepository;
import com.example.testcontainers.common.DatabaseCleanup;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers
public class FindHumanTest3 {

    @Autowired
    private DatabaseCleanup databaseCleanup;

    @Autowired
    private HumanRepository humanRepository;

    @BeforeEach
    void before() {
        humanRepository.save(new Human("abc", 20));
    }

    @AfterEach
    void after() {
        databaseCleanup.execute();
    }

    @Test
    void test() {
        Human findHuman = humanRepository.findByUsername("abc");
        System.out.println("findHuman.getId() = " + findHuman.getId());
        Assertions.assertThat(findHuman.getId()).isEqualTo(1);
    }
}
