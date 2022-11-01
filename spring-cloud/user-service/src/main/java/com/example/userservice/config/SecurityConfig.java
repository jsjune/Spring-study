package com.example.userservice.config;

import com.example.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final Environment env;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
//        http.authorizeRequests().antMatchers("/users/**").permitAll();

        //=====================================================
        AuthenticationManager authenticationManager = authenticationManager(http.getSharedObject(AuthenticationConfiguration.class));
        AuthenticationFilter authenticationFilter = getAuthenticationFilter(authenticationManager);
        //=====================================================

        http.authorizeRequests().antMatchers("/actuator/**").permitAll();
        http.authorizeRequests().antMatchers("/error/**").permitAll()
                .antMatchers("/**")
                .access("hasIpAddress('host.docker.internal')")
//                .access("hasIpAddress('192.168.56.1')")
//                .access("hasIpAddress('127.0.0.1')")
//                .hasIpAddress("127.0.0.1")
                .and()
                .addFilter(authenticationFilter);
        http.headers().frameOptions().disable(); //h2 프레임
        return http.build();
    }

//    @Bean
//    public SecurityFilterChain securityFilterChain2(AuthenticationManagerBuilder auth) throws Exception {
//        return (SecurityFilterChain) auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
//    }

    //=============================================================
    @Bean
    public AuthenticationManager authManager(HttpSecurity http)
            throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userService)
                .passwordEncoder(bCryptPasswordEncoder)
                .and()
                .build();
    }
    //=============================================================


    private AuthenticationFilter getAuthenticationFilter(AuthenticationManager authenticationManager) {
//        AuthenticationFilter authenticationFilter = new AuthenticationFilter(authenticationManager,userService,env);
//        authenticationFilter.setAuthenticationManager(authenticationManager);

        //==================================================
//        SecurityContextRepository contextRepository = new HttpSessionSecurityContextRepository();
//        authenticationFilter.setSecurityContextRepository(contextRepository);
        //==================================================

        return  new AuthenticationFilter(authenticationManager,userService,env);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
