package spring.security.jwtrefreshtoken.repository.redis;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import spring.security.jwtrefreshtoken.settings.IntegrationTestSupport;
import spring.security.jwtrefreshtoken.domain.RefreshToken;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class RefreshTokenRepositoryTest extends IntegrationTestSupport {
    @Autowired
    RefreshTokenRepository refreshTokenRepository;

    @BeforeEach
    void tearDown(){
        refreshTokenRepository.deleteAll();
    }

    @DisplayName("refreshToken을 redis에 저장한다.")
    @Test
    void save() {
        // given
        String refreshToken = "abcd";
        RefreshToken createRefreshToken = createRefreshToken(refreshToken);

        // when
        RefreshToken saveRefreshToken = refreshTokenRepository.save(createRefreshToken);

        // then
        assertThat(saveRefreshToken).isEqualTo(createRefreshToken);
    }

    @DisplayName("refreshToken을 UUID로 만든 후 조회한다.")
    @Test
    void findByRefreshToken() {
        // given
        String refreshToken = UUID.randomUUID().toString();
        RefreshToken createRefreshToken = createRefreshToken(refreshToken);
        RefreshToken saveRefreshToken = refreshTokenRepository.save(createRefreshToken);

        // when
        Optional<RefreshToken> findRefreshToken = refreshTokenRepository.findByRefreshToken(refreshToken);

        // then
        assertThat(findRefreshToken.get().getId()).isEqualTo(saveRefreshToken.getId());
    }

    @DisplayName("refreshToken이 TimeToLive 속성으로 지정한 시간이 지난 후 삭제된다.")
    @Test
    void TimeToLiveAfterRemove() throws InterruptedException {
        // given
        String refreshToken = "abcd";
        RefreshToken createRefreshToken = createRefreshToken(refreshToken);
        createRefreshToken.setExpiration(1L);
        refreshTokenRepository.save(createRefreshToken);

        // when
        Thread.sleep(1100);
        Optional<RefreshToken> findRefreshToken = refreshTokenRepository.findByRefreshToken(refreshToken);

        // then
        assertThat(findRefreshToken).isNotPresent();

    }

    private RefreshToken createRefreshToken(String UUID) {
        return RefreshToken.builder()
                .refreshToken(UUID)
                .email("abc@naver.com")
                .build();
    }
}
