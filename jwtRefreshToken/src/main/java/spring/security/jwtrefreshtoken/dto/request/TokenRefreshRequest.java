package spring.security.jwtrefreshtoken.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class TokenRefreshRequest {
    @NotBlank
    private String refreshToken;
}
