package sql.practice;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

@ExtendWith(MockitoExtension.class)
public class Prac6 {

    @Test
    void 테스트() {
        LocalDateTime a = LocalDateTime.now();
        String s = a.toString();
        System.out.println("s = " + s);
    }
}
