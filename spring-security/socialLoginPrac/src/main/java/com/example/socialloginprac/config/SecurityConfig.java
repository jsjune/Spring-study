package com.example.socialloginprac.config;

import com.example.socialloginprac.config.jwt.JwtAuthenticationFilter;
import com.example.socialloginprac.config.jwt.JwtAuthorizationFilter;
import com.example.socialloginprac.repository.UserRepository;
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
//@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig {

    private final UserRepository userRepository;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthenticationManager authenticationManager = authenticationManager(
            http.getSharedObject(AuthenticationConfiguration.class));
        JwtAuthenticationFilter jwtAuthenticationFilter = getJwtAuthenticationFilter(
            authenticationManager);
        jwtAuthenticationFilter.setUsernameParameter("email");

        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .addFilter(corsFilter())
            .formLogin().disable()
            .httpBasic().disable()
            .addFilter(jwtAuthenticationFilter)
            .addFilter(getJwtAuthorizationFilter(authenticationManager, userRepository))
            .authorizeRequests()
            .antMatchers("/api/v1/user/**")
            .access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
            .antMatchers("/api/v1/manager/**")
            .access("hasRole('ROLE_ADMIN' or hasRole('ROLE_MANAGER'))")
            .antMatchers("/api/v1/admin/**")
            .access("hasRole('ROLE_ADMIN')")
            .anyRequest().permitAll();

        return http.build();
    }

    private JwtAuthenticationFilter getJwtAuthenticationFilter(
        AuthenticationManager authenticationManager) {
        return new JwtAuthenticationFilter(authenticationManager);
    }

    private JwtAuthorizationFilter getJwtAuthorizationFilter(
        AuthenticationManager authenticationManager, UserRepository userRepository) {
        return new JwtAuthorizationFilter(authenticationManager, userRepository);
    }

    @Bean
    public AuthenticationManager authenticationManager(
        AuthenticationConfiguration authenticationConfiguration) throws Exception {
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
        source.registerCorsConfiguration("/api/**", config);
        return new CorsFilter(source);
    }
}
