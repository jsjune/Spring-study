package spring.security.jwtrefreshtoken.dto.request;

import lombok.Getter;

@Getter
public class LoginRequest {
    private String email;
    private String password;

    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
