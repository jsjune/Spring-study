package study.junit5prac.junitPrac;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class InstanceTest {
    int value = 1;

    @Test
    void test1() {
        value++;
        System.out.println(value);
    }

    @Test
    void test2() {
        value++;
        System.out.println(value);
    }

    @BeforeAll
    void beforeAll() {
        System.out.println("before all");
    }

    @AfterAll
    void afterAll() {
        System.out.println("after all");
    }
}
