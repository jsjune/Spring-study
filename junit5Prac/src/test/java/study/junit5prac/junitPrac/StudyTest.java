package study.junit5prac.junitPrac;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.*;
import study.junit5prac.domain.Study;
import study.junit5prac.domain.StudyStatus;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StudyTest {
    @Test
    @DisplayName("스터디 만들기")
    void create_new_study() {
        Study study = new Study(-10);
        assertNotNull(study);
        Assertions.assertEquals(StudyStatus.DRAFT, study.getStatus(), () -> "스터디를 처음 만들면 " + StudyStatus.DRAFT + " 상태다");
        assertTrue(study.getLimitCount() > 0, "스터디 최대 참석 가능 인원은 0보다 커야 한다.");
//        assertEquals(StudyStatus.DRAFT, study.getStatus(), new Supplier<String>() {
//            @Override
//            public String get() {
//                return "스터디를 처음 만들면 DRAFT 상태다.";
//            }
//        });
    }

    @Test
    void assert_all() {
        Study study = new Study(-10);
        assertAll(
                () -> assertNotNull(study),
                () -> assertEquals(StudyStatus.DRAFT, study.getStatus(),
                        () -> "스터디를 처음 만들면 " + StudyStatus.DRAFT + " 상태다"),
                () -> assertTrue(study.getLimitCount() > 0, "스터디 최대 참석 가능 인원은 0보다 커야 한다."));
    }

    @Test
    void create_new_study_again() {
        System.out.println("create1");
    }

    @Test
    void assert_throws() {
        assertThrows(IllegalArgumentException.class, () -> new Study(-10));
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Study(-10));
        assertEquals("limit은 0보다 커야 한다.", exception.getMessage());
    }

    @Test
    void assert_timeout() {
        assertTimeout(Duration.ofMillis(500), () -> {
            new Study(10);
            Thread.sleep(300);
        });
//        assertTimeoutPreemptively(Duration.ofMillis(100), () -> {
//            new Study(10);
//            Thread.sleep(300);
//        });
    }

    @Test
    void assert_that() {
        Study actual = new Study(10);
        assertThat(actual.getLimitCount()).isGreaterThan(0);
    }

    @Test
    void assume_true() {
        String test_env = System.getenv("TEST_ENV");
        System.out.println(test_env);
        assumeTrue("LOCAL".equalsIgnoreCase(test_env));
        System.out.println("test");
    }

    @Test
    void assuming_that() {
        String test_env = System.getenv("TEST_ENV");
        assumingThat("LOCAL".equalsIgnoreCase(test_env), () -> {
            Study actual = new Study(100);
            assertThat(actual.getLimitCount()).isGreaterThan(0);
        });

        assumingThat("JEONG".equalsIgnoreCase(test_env), () -> {
            Study actual = new Study(10);
            assertThat(actual.getLimitCount()).isGreaterThan(0);
        });
    }

    @Test
    @EnabledOnOs({OS.WINDOWS})
    void os_test_window() {
        System.out.println("window");
    }

    @Test
    @EnabledOnOs({OS.MAC})
    void os_test_mac() {
        System.out.println("mac");
    }

    @Test
    @EnabledOnJre({JRE.JAVA_8, JRE.JAVA_9, JRE.JAVA_10, JRE.JAVA_11})
    void java_version_test() {
        System.out.println("java11");
    }

//    @Test
//    @EnabledIfEnvironmentVariable(named = "TEST_ENV", matches = "LOCAL")

    @Test
    @Tag("fast")
    void tag_fast() {
        System.out.println("tag_fest");
    }

    @Test
    @FastTest
    void tag_fast_annotation() {
        System.out.println("tag_fast_annotation");
    }

    @DisplayName("반복테스트")
    @RepeatedTest(value = 10, name = "{displayName} ,{currentRepetition}/{totalRepetitions}")
    void repeatTest(RepetitionInfo c) {
        System.out.println("test " + c.getCurrentRepetition() + "/" + c.getTotalRepetitions());
    }

    @DisplayName("반복테스트1")
    @ParameterizedTest(name = "{index} {displayName} message={0}")
    @ValueSource(strings = {"날씨가", "많이", "추워지고", "있네요."})
//    @EmptySource
//    @NullSource
    @NullAndEmptySource
    void parameterizedTest(String message) {
        System.out.println(message);
    }

    @DisplayName("반복테스트2")
    @ParameterizedTest(name = "{index} {displayName} message={0}")
    @ValueSource(ints = {10, 20, 40})
    void parameterizedTest2(@ConvertWith(StudyConverter.class) Study study) {
        System.out.println(study.getLimitCount());
    }

    @DisplayName("반복테스트3")
    @ParameterizedTest(name = "{index} {displayName} message={0}")
    @CsvSource({"10, 자바 스터디", "20, 스프링"})
    void parameterizedTest3(Integer limit, String name) {
        Study study = new Study(limit, name);
        System.out.println(study);
    }

    @DisplayName("반복테스트4")
    @ParameterizedTest(name = "{index} {displayName} message={0}")
    @CsvSource({"10, 자바 스터디", "20, 스프링"})
    void parameterizedTest4(@AggregateWith(StudyAggregator.class) Study study) {
        System.out.println(study);
    }

    static class StudyAggregator implements ArgumentsAggregator {
        @Override
        public Object aggregateArguments(ArgumentsAccessor accessor, ParameterContext context) throws ArgumentsAggregationException {
            return new Study(accessor.getInteger(0), accessor.getString(1));
        }
    }

    static class StudyConverter extends SimpleArgumentConverter {
        @Override
        protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
            assertEquals(Study.class,targetType,"Can only convert to Study");
            return new Study(Integer.parseInt(source.toString()));
        }
    }

    @BeforeAll
    static void beforeAll() {
        System.out.println("before all");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("after all");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("before each");
    }

    @AfterEach
    void afterEach() {
        System.out.println("after each");
    }
}