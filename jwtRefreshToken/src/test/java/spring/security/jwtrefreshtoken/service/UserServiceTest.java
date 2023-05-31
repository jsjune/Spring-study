package spring.security.jwtrefreshtoken.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import spring.security.jwtrefreshtoken.config.jwt.JwtUtils;
import spring.security.jwtrefreshtoken.domain.User;
import spring.security.jwtrefreshtoken.dto.request.LoginRequest;
import spring.security.jwtrefreshtoken.dto.request.SignupRequest;
import spring.security.jwtrefreshtoken.dto.response.UserResponseDto;
import spring.security.jwtrefreshtoken.repository.UserRepository;
import spring.security.jwtrefreshtoken.repository.redis.RefreshTokenRepository;
import spring.security.jwtrefreshtoken.settings.IntegrationTestSupport;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.mockito.ArgumentMatchers.anyString;

class UserServiceTest extends IntegrationTestSupport {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RefreshTokenRepository refreshTokenRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtils jwtUtils;

    @DisplayName("회원 가입을 합니다.")
    @Test
    void registerUser() {
        // given
        String email = "aaa@naver.com";
        String password = "1234";
        String username = "가나다";

        SignupRequest request = new SignupRequest(username, email, password);

        // when
        String answer = userService.registerUser(request);
        List<User> all = userRepository.findAll();

        // then
        assertThat(answer).isEqualTo("success");
        assertThat(all.get(0))
                .extracting("email", "username")
                .contains(email, username);
        assertThat(bCryptPasswordEncoder.matches(password, all.get(0).getPassword())).isTrue();
    }

    @DisplayName("로그인을 합니다.")
    @Test
    void login() {
        // given
        String email = "aaa@naver.com";
        String password = "1234";
        User user = User.register()
                .email(email)
                .password(bCryptPasswordEncoder.encode(password))
                .username("가나다")
                .roles("USER_ROLE")
                .build();
        userRepository.save(user);
        LoginRequest request = new LoginRequest(email, password);

        // when
        UserResponseDto.TokenInfo login = userService.login(request);

        // then
        assertThat(login.getEmail()).isEqualTo(email);
    }
}
