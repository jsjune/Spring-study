package com.example.sociallogin2.model.users;


import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Data
@NoArgsConstructor
public class User {
    private String registrationId;
    private String id;
    private String username;
    private String password;
    private String provider;
    private String email;
    private List<? extends GrantedAuthority> authorities;

    @Builder
    public User(String registrationId, String id, String username, String password,
        String email,
        List<? extends GrantedAuthority> authorities) {
        this.registrationId = registrationId;
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.authorities = authorities;
    }
}
