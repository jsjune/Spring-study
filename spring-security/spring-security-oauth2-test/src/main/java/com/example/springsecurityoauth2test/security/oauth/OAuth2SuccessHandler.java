package com.example.springsecurityoauth2test.security.oauth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.springsecurityoauth2test.model.User;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.springsecurityoauth2test.security.UserDetailsImpl;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        UserDetailsImpl authorities = ((UserDetailsImpl) authentication.getPrincipal());
            String jwtToken = JWT.create()
                    .withSubject("jwtToken")
                    .withExpiresAt(new Date(System.currentTimeMillis() + 864000000))
                    .withClaim("id", authorities.getUser().getId())
                    .withClaim("email", authorities.getUser().getEmail())
                    .sign(Algorithm.HMAC512("test"));
        System.out.println("================================");
        System.out.println(jwtToken);
        System.out.println("================================");
        response.addHeader("Authorization", "Bearer " + jwtToken);
//        System.out.println(response.getHeaderNames());
//        System.out.println(response.getHeader("Authorization"));
//        System.out.println(response.getStatus());
    }
}
