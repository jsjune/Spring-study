package com.example.sociallogin.model;


import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.security.core.GrantedAuthority;

@Data
@NoArgsConstructor
@Entity
public class User {
    private String registrationId;
    @Id
    private String id;
    private String username;
    private String password;
    private String provider;
    private String email;
//    private List<String> authorities;

    @Builder
    public User(String registrationId, String id, String username, String password,
        String email,
        List<String> authorities) {
        this.registrationId = registrationId;
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
//        this.authorities = authorities;
    }
}
