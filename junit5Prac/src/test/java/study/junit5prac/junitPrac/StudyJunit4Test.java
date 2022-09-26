package study.junit5prac.junitPrac;

import org.junit.Before;
import org.junit.Test;

public class StudyJunit4Test {

    @Before
    public void before() {
        System.out.println("before");
    }

    @Test
    public void createTest1() {
        System.out.println("test1");
    }

    @Test
    public void createTest2() {
        System.out.println("test2");
    }
}
