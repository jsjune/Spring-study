package spring.security.jwtrefreshtoken.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.jdbc.Sql;
import spring.security.jwtrefreshtoken.common.exception.GlobalException;
import spring.security.jwtrefreshtoken.config.jwt.JwtUtils;
import spring.security.jwtrefreshtoken.domain.RefreshToken;
import spring.security.jwtrefreshtoken.domain.User;
import spring.security.jwtrefreshtoken.dto.request.LoginRequest;
import spring.security.jwtrefreshtoken.dto.request.SignupRequest;
import spring.security.jwtrefreshtoken.dto.request.TokenRefreshRequest;
import spring.security.jwtrefreshtoken.dto.response.UserResponseDto;
import spring.security.jwtrefreshtoken.repository.UserRepository;
import spring.security.jwtrefreshtoken.repository.redis.RefreshTokenRepository;
import spring.security.jwtrefreshtoken.settings.IntegrationTestSupport;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static spring.security.jwtrefreshtoken.common.exception.ErrorCode.EXIST_EMAIL;
import static spring.security.jwtrefreshtoken.common.exception.ErrorCode.EXPIRED_REFRESH_TOKEN;

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


    @AfterEach
    void tearDown(){
        userRepository.deleteAllInBatch();
        refreshTokenRepository.deleteAll();
    }

    @DisplayName("이메일이 이미 존재하여 회원 가입을 실패합니다.")
    @Test
    void test() {
        // given
        String email = "aaa@naver.com";
        String password = "1234";
        String username = "가나다";

        User user = User.register()
                .email(email)
                .username(username)
                .password(bCryptPasswordEncoder.encode(password))
                .roles("ROLE_USER")
                .build();
        userRepository.save(user);
        SignupRequest request = new SignupRequest(username, email, password);

        // when // then
        assertThatThrownBy(() -> userService.registerUser(request))
                .isInstanceOf(GlobalException.class)
                .satisfies(ex -> {
                    assertThat(((GlobalException) ex).getErrorCode()).isEqualTo(EXIST_EMAIL);
                });

    }

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

    @DisplayName("refreshToken이 만료되어 예외처리 합니다.")
    @Test
    void reissueExpirationRefreshToken() throws InterruptedException {
        // given
        TokenRefreshRequest request = new TokenRefreshRequest(UUID.randomUUID().toString());
        RefreshToken refreshToken = RefreshToken.builder()
                .email("aaa@naver.com")
                .refreshToken(request.getRefreshToken())
                .build();
        refreshToken.setExpiration(1L);
        refreshTokenRepository.save(refreshToken);
        Thread.sleep(1100L);

        // when // then
        assertThatThrownBy(() -> userService.reissue(request))
                .isInstanceOf(GlobalException.class)
                .satisfies(ex -> {
                    assertThat(((GlobalException) ex).getErrorCode()).isEqualTo(EXPIRED_REFRESH_TOKEN);
                });
    }

    @DisplayName("refreshToken을 통해 accessToken을 재발급 받습니다.")
    @Test
    void reissue() {
        // given
        TokenRefreshRequest request = new TokenRefreshRequest(UUID.randomUUID().toString());
        RefreshToken refreshToken = RefreshToken.builder()
                .email("aaa@naver.com")
                .refreshToken(request.getRefreshToken())
                .build();
        refreshTokenRepository.save(refreshToken);
        String accessToken = "accessToken";

        JwtUtils jwtUtilsMock = mock(JwtUtils.class);
        when(jwtUtilsMock.generateAccessTokenFromEmail(anyString())).thenReturn(accessToken);

        UserService userService = new UserService(userRepository, refreshTokenRepository, bCryptPasswordEncoder, authenticationManager, jwtUtilsMock);

        // when
        UserResponseDto.TokenInfo response = userService.reissue(request);

        // then
        assertThat(response.getEmail()).isEqualTo(refreshToken.getEmail());
        assertThat(response.getRefreshToken()).isEqualTo(refreshToken.getRefreshToken());
        assertThat(response.getAccessToken()).isEqualTo(accessToken);

        verify(jwtUtilsMock).generateAccessTokenFromEmail(refreshToken.getEmail());

    }
}
