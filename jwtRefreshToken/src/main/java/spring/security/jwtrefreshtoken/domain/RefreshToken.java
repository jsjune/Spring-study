package spring.security.jwtrefreshtoken.domain;

import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.time.Instant;

@RedisHash(value = "refresh")
@Getter
@Setter
public class RefreshToken {
    @Id
    private Long id;
    private String email;
    private Instant expiryDate;
    @Indexed
    private String refreshToken;

    @Builder
    public RefreshToken(String email, Instant expiryDate, String refreshToken) {
        this.email = email;
        this.expiryDate = expiryDate;
        this.refreshToken = refreshToken;
    }
}
