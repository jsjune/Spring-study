package com.example.notificationservice.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestServiceTestEntity {
    @Autowired
    private TestService testService;
    @Autowired
    private TestRepository testRepository;

    @BeforeEach
    void setUp() {
        testRepository.save(TestEntity.builder().id(1L).name("test").build());
    }

    @Test
    public void deleteTest() {
        testService.testDelete(1L);
        assertTrue(testRepository.findById(1L).isEmpty());
    }

    @Test
    public void transactionDeleteTest() {
        testService.transactionTestDelete(1L);
        assertTrue(testRepository.findById(1L).isEmpty());
    }
}