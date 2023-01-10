package com.example.springsecurityoauth2test.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.springsecurityoauth2test.model.User;
import com.example.springsecurityoauth2test.repository.UserRepository;
import com.example.springsecurityoauth2test.security.UserDetailsImpl;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Slf4j
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private  UserRepository userRepository;

    public JwtAuthorizationFilter(
        AuthenticationManager authenticationManager,
        UserRepository userRepository) {
        super(authenticationManager);
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
        FilterChain chain) throws IOException, ServletException {
        log.info("인증이나 권한이 필요한 주소 요청이 됨.");
        String jwtHeader = request.getHeader("Authorization");
        System.out.println(jwtHeader);
        if (jwtHeader == null || !jwtHeader.startsWith("Bearer ")) {
            log.info("Authentication 헤더가 있는지 확인 -> 존재 X");
            chain.doFilter(request, response);
            return;
        }
        String jwtToken = jwtHeader.replace("Bearer ", "");
        String email = JWT.require(Algorithm.HMAC512("test")).build()
            .verify(jwtToken)
            .getClaim("email").asString();

        if (email != null) {
            User user = userRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("존재하지 않는 이메일입니다. 다시 로그인 해보세요")
            );
            UserDetails userDetails = new UserDetailsImpl(user);
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null,
                userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request, response);
    }
}
