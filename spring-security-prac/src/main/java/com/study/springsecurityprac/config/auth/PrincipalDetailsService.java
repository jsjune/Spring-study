package com.study.springsecurityprac.config.auth;

import com.study.springsecurityprac.domain.User;
import com.study.springsecurityprac.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/* 시큐리티 설정에서 loginProcessingUrl();
*  login 요청이 오면 자동으로 userDetailsService 타입으로 IoC되어 있는 loadUserByUsername 함수가 실행*/

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    // 시큐리티 session(내부 Authentication(내부 userDetails))
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            return new PrincipalDetails(user);
        }
        return null;
    }
}
