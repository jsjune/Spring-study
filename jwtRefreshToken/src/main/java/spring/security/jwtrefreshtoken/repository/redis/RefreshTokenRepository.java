package spring.security.jwtrefreshtoken.repository.redis;

import org.springframework.data.repository.CrudRepository;
import spring.security.jwtrefreshtoken.domain.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByRefreshToken(String refreshToken);

    void deleteByRefreshToken(String refreshTokenCheck);
}
