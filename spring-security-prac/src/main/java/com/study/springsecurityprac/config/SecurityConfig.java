package com.study.springsecurityprac.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true) // secured 어노테이션 활성화, preAuthorize,postAuthorize 어노테이션 활성화
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder encodePwd() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf().disable().authorizeRequests() // OAuth2, jwt 사용시 csrf 불필요
//                .antMatchers("/api/**").authenticated() //api 요청에 대해서는 로그인을 요구함
            .antMatchers("/user/**").authenticated() // 인증만 되면 들어갈 수 있는 주소
            .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
            .antMatchers("/manager/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
            .anyRequest().permitAll()
            .and()
            .formLogin()
            .loginPage("/loginForm") // 어떤 url을 가든 로그인 페이지로 지정
//            .usernameParameter("email")
            .loginProcessingUrl("/login")
            .defaultSuccessUrl("/");
        return http.build();
    }
}
