package com.example.jwt.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.jwt.config.auth.UserDetailsImpl;
import com.example.jwt.dto.RequestLogin;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Date;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/*
 * POST /login 요청해서 username, password 전송하면
 * UsernamePasswordAuthenticationFilter 동작을 함.*/
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    // /login 요청을 하면 로그인 시도를 위해서 실행되는 함수
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
        HttpServletResponse response) throws AuthenticationException {
        log.info("로그인 시도중");

        /* 1. username, password 받아서 */
        try {
            RequestLogin user = new ObjectMapper().readValue(request.getInputStream(),
                RequestLogin.class);
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(user.getEmail(),
                user.getPassword());

            // UserDetailsService의 loadUserByUsername() 함수가 실행된 후 정상이면 authentication이 리턴됨
            // db에 있는 username과 password가 일치한다.
            Authentication authentication = authenticationManager.authenticate(authRequest);
//            UserDetailsImpl userDetails = (UserDetailsImpl)authentication.getPrincipal();
//            System.out.println("==========================================================");
//            System.out.println(userDetails.getUser().getEmail()); // 로그인 정상적으로 되었다는 뜻.
            // authentication 객체가 session영역에 저장을 해야하고 그 방법이 return 해주면 됨.
            // 리턴의 이유는 권한 관리를 security가 대신 해주기 때문에 편하려고 하는거임.
            // 굳이 JWT 토큰을 사용하면서 세션을 만들 이유가 없음. 근데 단지 권한 처리때문에 session 넣어준다.
            return authentication;

            // 밑에 실행안됨
//            setDetails(request, authRequest);
//            return getAuthenticationManager().authenticate(
//                new UsernamePasswordAuthenticationToken(
//                    user.getEmail(),
//                    user.getPassword(),
//                    new ArrayList<>()
//                )
//            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /*
     * 2. 정상인지 로그인 시도
     * authenticationManager로 로그인 시도를 하면
     * UserDetailsServiceImpl 호출 -> loadUserByUsername() 실행
     * 3. UserDetailsImpl를 세션에 담고 (권한 관리를 위해)
     * 4. JWT 토큰을 만들어서 응답해주면됨 */

    // attemptAuthentication실행 후 인증이 정상적으로 되었으면 successfulAuthentication 함수가 실행
    // JWT 토큰을 만들어서 request 요청한 사용자에게 JWT 토큰을 response 해주면 됨
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
        HttpServletResponse response, FilterChain chain, Authentication authResult)
        throws IOException, ServletException {
        log.info("successfulAuthentication 실행됨 : 인증 완료");
        UserDetailsImpl userDetailsImpl = (UserDetailsImpl)authResult.getPrincipal();
        String jwtToken = JWT.create()
            .withSubject("token")
            .withExpiresAt(new Date(System.currentTimeMillis()+JwtProperties.EXPIRATION_TIME))
            .withClaim("id", userDetailsImpl.getUser().getId())
            .withClaim("email", userDetailsImpl.getUser().getEmail())
            .sign(Algorithm.HMAC512(JwtProperties.SECRET));
        response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX + jwtToken);
    }
}
