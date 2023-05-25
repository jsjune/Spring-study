package spring.security.jwtrefreshtoken.domain;

import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

import static spring.security.jwtrefreshtoken.config.jwt.JwtProperties.REFRESH_TOKEN_EXPIRE_TIME_FOR_REDIS;

@RedisHash(value = "refresh")
@Getter
@Setter
public class RefreshToken {
    @Id
    private Long id;
    private String email;
    @TimeToLive
    private Long expiration = REFRESH_TOKEN_EXPIRE_TIME_FOR_REDIS;
    @Indexed
    private String refreshToken;

    @Builder
    public RefreshToken(String email, String refreshToken) {
        this.email = email;
        this.refreshToken = refreshToken;
    }
}
