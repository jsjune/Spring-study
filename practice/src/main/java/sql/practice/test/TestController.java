package sql.practice.test;

import java.util.ArrayList;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class TestController {

    @GetMapping("/test")
    public String test() {
        ArrayList<TestDto> arr = new ArrayList<>();

        log.info("test");
        for (int i = 0; i < 10; i++) {
            arr.add(new TestDto(i, "test" + i));
        }
        return "hello";
    }

    @GetMapping("/test1")
    public String test1() {
        log.warn("error");
        throw new RuntimeException("에러");
    }

    private static class TestDto {
        int a;
        String b;

        public TestDto(int a, String b) {
            this.a = a;
            this.b = b;
        }
    }
}
