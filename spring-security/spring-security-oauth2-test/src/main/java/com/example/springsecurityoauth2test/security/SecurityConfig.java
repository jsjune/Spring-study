package com.example.springsecurityoauth2test.security;

import com.example.springsecurityoauth2test.repository.UserRepository;
import com.example.springsecurityoauth2test.security.jwt.JwtAuthenticationFilter;
import com.example.springsecurityoauth2test.security.jwt.JwtAuthorizationFilter;
import com.example.springsecurityoauth2test.security.oauth.OAuth2SuccessHandler;
import com.example.springsecurityoauth2test.security.oauth.Oauth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserRepository userRepository;
    private final Oauth2UserService oauth2UserService;
    private final OAuth2SuccessHandler oAuth2SuccessHandler;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        AuthenticationManager authenticationManager = authenticationManager(
            http.getSharedObject(AuthenticationConfiguration.class));
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(
            authenticationManager);
        jwtAuthenticationFilter.setUsernameParameter("email");

        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .addFilter(corsFilter())
            .formLogin().disable()
            .httpBasic().disable()
            .addFilter(jwtAuthenticationFilter)
            .addFilter(new JwtAuthorizationFilter(authenticationManager, userRepository))
            .authorizeRequests()
            .antMatchers("/user/**").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
            .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
            .antMatchers("/**").permitAll()
            .anyRequest().authenticated();
        http.oauth2Login()
            .successHandler(oAuth2SuccessHandler)
            .userInfoEndpoint()
            .userService(oauth2UserService);
        http.logout().logoutSuccessUrl("/");
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true); // 내서버가 응답을 할 때 json을 자바스크립트에서 처리할 수 있게 할지를 설정하는 것
        config.addAllowedOrigin("*"); // 모든 ip에 응답을 허용하겠다.
        config.addAllowedHeader("*"); // 모든 header에 응답을 허용하겠다.
        config.addAllowedMethod("*"); // 모든 api 메소드 요청을 허용하겠다.
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
