package sql.practice.테스트격리;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sql.practice.test3.User;
import sql.practice.test3.UserRepository;

@SpringBootTest
public class TestA {
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void before() {
        User user = new User("가나다");
        userRepository.save(user);
    }

    @Test
    void findId() {
        User user = userRepository.findByUsername("가나다");
        System.out.println("id : " + user.getId());
    }

    @AfterEach
    void after() {
        userRepository.deleteAll();
    }
}
