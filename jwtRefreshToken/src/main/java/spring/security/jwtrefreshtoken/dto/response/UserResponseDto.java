package spring.security.jwtrefreshtoken.dto.response;

import lombok.Getter;

public class UserResponseDto {

    @Getter
    public static class TokenInfo {
        private String email;
        private String accessToken;
        private String refreshToken;

        public TokenInfo(String email, String accessToken, String refreshToken) {
            this.email = email;
            this.accessToken = accessToken;
            this.refreshToken = refreshToken;
        }
    }

}
