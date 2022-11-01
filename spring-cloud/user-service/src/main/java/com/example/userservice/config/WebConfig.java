//package com.example.userservice.config;
//
//import com.example.userservice.service.UserService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.core.env.Environment;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class WebConfig extends WebSecurityConfigurerAdapter{
//
//    private final UserService userService;
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;
//    private final Environment env;
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable();
////        http.authorizeRequests().antMatchers("/users/**").permitAll();
//
//        http.authorizeRequests().antMatchers("/**")
//                .hasIpAddress("127.0.0.1") // <- IP 변경
//                .and()
//                .addFilter(getAuthenticationFilter());
//
//
//        http.headers().frameOptions().disable();
//    }
//
//    private AuthenticationFilter getAuthenticationFilter() throws Exception {
//        AuthenticationFilter authenticationFilter =
//                new AuthenticationFilter(authenticationManager(), userService, env);
//
//        return authenticationFilter;
//    }
//
//    // select pwd from users where email=?
//    // db_pwd(encrypted) == input_pwd(encrypted)
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
//    }
//}
