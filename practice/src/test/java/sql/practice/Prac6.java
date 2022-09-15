package sql.practice;


import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class Prac6 {
    @Test
    void 테스트() {
        LocalDateTime a = LocalDateTime.now();
        String s = a.toString();
        System.out.println("s = " + s);
    }
}
