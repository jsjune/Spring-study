package spring.security.jwtrefreshtoken.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import spring.security.jwtrefreshtoken.domain.User;
import spring.security.jwtrefreshtoken.dto.request.LoginRequest;
import spring.security.jwtrefreshtoken.dto.request.SignupRequest;
import spring.security.jwtrefreshtoken.repository.UserRepository;
import spring.security.jwtrefreshtoken.service.UserService;
import spring.security.jwtrefreshtoken.settings.ControllerTestSupport;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserControllerTest extends ControllerTestSupport {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    protected UserService userService;

    @BeforeEach
    public void setUp() {
        userRepository.save(new User("가나다", bCryptPasswordEncoder.encode("1234"), "abc@naver.com", "USER_ROLE"));
    }
    @AfterEach
    public void tearDown() {
        userRepository.deleteAllInBatch();
    }

    @DisplayName("회원가입을 성공합니다.")
    @Test
    void registerUserSuccess() throws Exception {
        // given
        SignupRequest request = new SignupRequest("abc", "aaa@naver.com", "1234");

        // when // then
        mockMvc.perform(
                        post("/auth/signup")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    @DisplayName("로그인을 성공합니다.")
    @Test
    void loginSuccess() throws Exception {
        // given
        LoginRequest request = new LoginRequest("abc@naver.com", "1234");

        // when // then
        mockMvc.perform(
                        post("/auth/login")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    @DisplayName("계정이 존재하지않아 로그인에 실패합니다.")
    @Test
    void loginInvalidEmailFail() throws Exception {
        // given
        LoginRequest request = new LoginRequest("abcabc@naver.com", "1234");

        // when // then
        mockMvc.perform(
                        post("/auth/login")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().is(401))
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.error.message").value("계정이 존재하지 않습니다."));
    }

    @DisplayName("비밀번호가 틀려 로그인에 실패합니다.")
    @Test
    void loginInvalidPasswordFail() throws Exception {
        // given
        LoginRequest request = new LoginRequest("abc@naver.com", "12345");

        // when // then
        mockMvc.perform(
                        post("/auth/login")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().is(401))
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.error.message").value("비밀번호가 불일치 합니다."));
    }
}
