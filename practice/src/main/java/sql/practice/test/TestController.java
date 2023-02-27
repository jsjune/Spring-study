package sql.practice.test;

import java.util.ArrayList;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public String test() {
        ArrayList<TestDto> arr = new ArrayList<>();
        for (int i = 0; i < 100_000_000; i++) {
            arr.add(new TestDto(i, "test" + i));
        }
        return "hello";
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
