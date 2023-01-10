package com.example.springsecurityoauth2test.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.springsecurityoauth2test.dto.RequestLogin;
import com.example.springsecurityoauth2test.security.UserDetailsImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Date;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
        HttpServletResponse response) throws AuthenticationException {
        log.info("로그인 시도중");
        try{
            RequestLogin user = new ObjectMapper().readValue(request.getInputStream(),
                RequestLogin.class);
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                user.getEmail(), user.getPassword());
            Authentication authentication = authenticationManager.authenticate(authRequest);
            return authentication;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
        HttpServletResponse response, FilterChain chain, Authentication authResult) {
        log.info("successfulAuthentication 실행됨 : 인증 완료");
        UserDetailsImpl userDetailsImpl = (UserDetailsImpl) authResult.getPrincipal();
        String jwtToken = JWT.create()
            .withSubject("jwtToken")
            .withExpiresAt(new Date(System.currentTimeMillis() + 864000000))
            .withClaim("id", userDetailsImpl.getUser().getId())
            .withClaim("email", userDetailsImpl.getUser().getEmail())
            .sign(Algorithm.HMAC512("test"));
        response.addHeader("Authorization",
            "Bearer " + jwtToken);
    }
}
