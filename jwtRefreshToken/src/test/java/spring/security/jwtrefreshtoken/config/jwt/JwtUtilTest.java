package spring.security.jwtrefreshtoken.config.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mock.web.MockHttpServletRequest;
import spring.security.jwtrefreshtoken.settings.IntegrationTestSupport;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static spring.security.jwtrefreshtoken.common.exception.ErrorCode.EXPIRED_ACCESS_TOKEN;
import static spring.security.jwtrefreshtoken.config.jwt.JwtProperties.*;

class JwtUtilTest extends IntegrationTestSupport {
    @Autowired
    private JwtUtils jwtUtils;
    @Value("${jwt.app.jwtSecret}")
    private String jwtSecret;

    @DisplayName("이메일로 accessToken을 생성합니다.")
    @Test
    void generateAccessTokenFromEmail() {
        // given
        String email = "abc@naver.com";

        // when
        String accessToken = jwtUtils.generateAccessTokenFromEmail(email);

        // then
        assertThat(accessToken).isNotNull();
    }

    @DisplayName("accessToken으로 이메일을 추출합니다.")
    @Test
    void getEmailFromJwtToken() {
        // given
        String email = "abc@naver.com";
        String accessToken = jwtUtils.generateAccessTokenFromEmail(email);

        // when
        String emailFromJwtToken = jwtUtils.getEmailFromJwtToken(accessToken);

        // then
        assertThat(emailFromJwtToken).isEqualTo(email);
    }

    @DisplayName("HttpServlet request에서 헤더값 추출해서 accessToken을 가져옵니다.")
    @Test
    void parseJwtToken() {
        // given
        MockHttpServletRequest request = new MockHttpServletRequest();
        String accessToken = "accessToken";
        String token = TOKEN_PREFIX + accessToken;
        request.addHeader(HEADER_STRING, token);

        // when
        String jwtToken = jwtUtils.parseJwtToken(request);

        // then
        assertThat(jwtToken).isEqualTo(accessToken);
    }

    @DisplayName("accessToken이 만료되면 예외가 발생한다.")
    @Test
    void EXPIRED_ACCESS_TOKEN() throws InterruptedException {
        // given
        LocalDateTime current = LocalDateTime.of(2023, 5, 5, 12, 0, 0);
        Date date = Timestamp.valueOf(current);
        String accessToken = createAccessToken(date);
        MockHttpServletRequest request = new MockHttpServletRequest();

        // when
        Thread.sleep(1);
        boolean check = jwtUtils.validateJwtToken(accessToken, request);
        String jwtException = (String) request.getAttribute(EXCEPTION);

        // then
        assertThat(check).isFalse();
        assertThat(jwtException).isEqualTo(EXPIRED_ACCESS_TOKEN.getCode());
    }

    private String createAccessToken(Date date) {
        return Jwts.builder()
                .setSubject("abc@naver.com")
                .setIssuedAt(date)
                .setExpiration(new Date(date.getTime()))
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }

}
