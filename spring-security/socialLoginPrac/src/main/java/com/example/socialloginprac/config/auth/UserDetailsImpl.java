package com.example.socialloginprac.config.auth;

import com.example.socialloginprac.domain.User;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

@Getter
public class UserDetailsImpl implements UserDetails, OAuth2User {
    private User user;
    private Map<String, Object> attributes;

    // 일반 시큐리티 로그인시 사용
    public UserDetailsImpl(User user) {
        this.user = user;
    }

    // OAuth2.0 로그인시 사용
    public UserDetailsImpl(User user, Map<String, Object> attributes) {
        this.user = user;
        this.attributes = attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        user.getRoleList().forEach(r -> {
            authorities.add(() -> r);
        });
//        user.getRoleList().forEach(r -> authorities.add(() -> r));
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getName() {
        return user.getId()+"";
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }
}
