package io.security.oauth2.springsecurityoauth2.practice;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

//@EnableWebSecurity
public class SecurityConfig {
    //    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeRequests().anyRequest().authenticated();
//        http.formLogin();
////        http.apply(new CustomSecurityConfigurer().setFlag(false));
//        return http.build();
//    }
//
//    @Bean
//    SecurityFilterChain securityFilterChain2(HttpSecurity http) throws Exception {
//        http.authorizeRequests().anyRequest().authenticated();
////        http.formLogin();
//        http.httpBasic();
//        return http.build();
//    }

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().authenticated();
        http.formLogin();
//        http.httpBasic();
        http.exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint());
        return http.build();
    }
}
