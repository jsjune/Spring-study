package spring.security.jwtrefreshtoken.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import spring.security.jwtrefreshtoken.settings.IntegrationTestSupport;
import spring.security.jwtrefreshtoken.domain.User;

import static org.assertj.core.api.Assertions.assertThat;

class UserRepositoryTest extends IntegrationTestSupport {
    @Autowired
    UserRepository userRepository;

    @AfterEach
    void tearDown(){
        userRepository.deleteAllInBatch();
    }

    @DisplayName("이메일이 존재하는지 확인한다.")
    @Test
    void existsByEmail() {
        // given
        String email = "abc@naver.com";
        User user = createUser(email);
        userRepository.save(user);

        // when
        boolean existsEmail = userRepository.existsByEmail(email);

        // then
        assertThat(existsEmail).isTrue();

    }

    private static User createUser(String email) {
        return User.register()
                .email(email)
                .password("1234")
                .username("가나다")
                .roles("USER_ROLE")
                .build();
    }

}
