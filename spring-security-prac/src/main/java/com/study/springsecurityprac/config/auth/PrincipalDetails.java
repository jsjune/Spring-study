package com.study.springsecurityprac.config.auth;

import com.study.springsecurityprac.domain.User;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/*
    시큐리티가 /login 주소 요청이 오면 낚아채서 로그인을 진행시킨다.
    로그인을 진행이 완료가 되면 시큐리티 session을 만들어준다. (SecurityContextHolder)
    session에 들어갈 수 있는 정보가 정해져 있다.
    오브젝트 타입 => Authentication 타입 객체
    Authentication 안에 User정보가 있어야 됨
    User 오브젝트타입 => UserDetails 타입 객체

    Security Session => Authentication => UserDetails
*/
public class PrincipalDetails implements UserDetails {

    private User user;

    public PrincipalDetails(User user) {
        this.user = user;
    }

    // 해당 User의 권한을 리턴하는 곳
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getRole();
            }
        });
        return collect;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
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
        // 1년동안 회원이 로그인을 안하면 휴면계정
        // 현재시간 - 로긴시간 => 1년을 초과하면 return false;
        return true;
    }
}
