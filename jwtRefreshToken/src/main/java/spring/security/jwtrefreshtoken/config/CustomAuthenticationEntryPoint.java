package spring.security.jwtrefreshtoken.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import spring.security.jwtrefreshtoken.common.dto.ResponseDto;
import spring.security.jwtrefreshtoken.common.exception.ErrorCode;
import spring.security.jwtrefreshtoken.common.exception.ErrorResponse;

import java.io.IOException;

import static spring.security.jwtrefreshtoken.common.exception.ErrorCode.*;
import static spring.security.jwtrefreshtoken.config.jwt.JwtProperties.EXCEPTION;


@Component
@Slf4j
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        String loginException = authException.getClass().getSimpleName();
        String jwtException = (String) request.getAttribute(EXCEPTION);
        ErrorCode errorCode;

        if (jwtException != null) {
            if (jwtException.equals(INVALID_JWT_SIGNATURE.getCode())) {
                errorCode = INVALID_JWT_SIGNATURE;
                setResponse(response, errorCode);
            } else if (jwtException.equals(INVALID_JWT_TOKEN.getCode())) {
                errorCode = INVALID_JWT_TOKEN;
                setResponse(response, errorCode);
            } else if (jwtException.equals(EXPIRED_ACCESS_TOKEN.getCode())) {
                errorCode = EXPIRED_ACCESS_TOKEN;
                setResponse(response, errorCode);
            } else if (jwtException.equals(UNSUPPORTED_JWT_TOKEN.getCode())) {
                errorCode = UNSUPPORTED_JWT_TOKEN;
                setResponse(response, errorCode);
            } else if (jwtException.equals(CLAIMS_EMPTY.getCode())) {
                errorCode = CLAIMS_EMPTY;
                setResponse(response, errorCode);
            }
        } else {
            if (loginException.equals(UsernameNotFoundException.name())) {
                errorCode = UsernameNotFoundException;
                setResponse(response, errorCode);
            } else if (loginException.equals(BadCredentialsException.name())) {
                errorCode = BadCredentialsException;
                setResponse(response, errorCode);
            } else if (loginException.equals(AccountExpiredException.name())) {
                errorCode = AccountExpiredException;
                setResponse(response, errorCode);
            } else if (loginException.equals(CredentialsExpiredException.name())) {
                errorCode = CredentialsExpiredException;
                setResponse(response, errorCode);
            } else if (loginException.equals(DisabledException.name())) {
                errorCode = DisabledException;
                setResponse(response, errorCode);
            } else if (loginException.equals(LockedException.name())) {
                errorCode = LockedException;
                setResponse(response, errorCode);
            } else {
                errorCode = NoneException;
                setResponse(response, errorCode);
            }
        }
    }

    private void setResponse(HttpServletResponse response, ErrorCode errorCode) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//        ErrorResponse errorResponse = new ErrorResponse(errorCode.getCode(), errorCode.getMessage());
        ResponseDto<Object> fail = ResponseDto.fail(errorCode.getCode(), errorCode.getMessage());
        response.getWriter().write(objectMapper.writeValueAsString(fail));
    }
}
