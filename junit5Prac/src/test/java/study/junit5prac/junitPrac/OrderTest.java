package study.junit5prac.junitPrac;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;

// 시나리오 테스트
//@ExtendWith(FIndSlowTestExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderTest {

//    @RegisterExtension
//    static FIndSlowTestExtension fIndSlowTestExtension = new FIndSlowTestExtension(1000);

    int value = 0;

    @Order(1)
    @Test
//    @FastTest
    void test_1() {
        value++;
        System.out.println("1");
        System.out.println("value = " + value);
    }

    @Order(3)
    @Test
    void test_3() {
        value++;
        System.out.println("3");
        System.out.println("value = " + value);
    }

    @Order(2)
    @Test
//    @SlowTest
    void test_2() throws InterruptedException {
        Thread.sleep(1005L);
        value++;
        System.out.println("2");
        System.out.println("value = " + value);
    }
}
