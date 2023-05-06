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
import spring.security.jwtrefreshtoken.common.exception.ErrorCode;
import spring.security.jwtrefreshtoken.common.exception.ErrorResponse;

import java.io.IOException;

@Component
@Slf4j
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        String loginException = authException.getClass().getSimpleName();
        String jwtException = (String) request.getAttribute("exception");
        ErrorCode errorCode;

        if (jwtException != null) {
            if (jwtException.equals(ErrorCode.INVALID_JWT_SIGNATURE.getCode())) {
                errorCode = ErrorCode.INVALID_JWT_SIGNATURE;
                setResponse(response, errorCode);
            } else if (jwtException.equals(ErrorCode.INVALID_JWT_TOKEN.getCode())) {
                errorCode = ErrorCode.INVALID_JWT_TOKEN;
                setResponse(response, errorCode);
            } else if (jwtException.equals(ErrorCode.EXPIRED_ACCESS_TOKEN.getCode())) {
                errorCode = ErrorCode.EXPIRED_ACCESS_TOKEN;
                setResponse(response, errorCode);
            } else if (jwtException.equals(ErrorCode.UNSUPPORTED_JWT_TOKEN.getCode())) {
                errorCode = ErrorCode.UNSUPPORTED_JWT_TOKEN;
                setResponse(response, errorCode);
            } else if (jwtException.equals(ErrorCode.CLAIMS_EMPTY.getCode())) {
                errorCode = ErrorCode.CLAIMS_EMPTY;
                setResponse(response, errorCode);
            }
        } else {
            if (loginException.equals(ErrorCode.BadCredentialsException.name())) {
                errorCode = ErrorCode.BadCredentialsException;
                setResponse(response, errorCode);
            } else if (loginException.equals(ErrorCode.UsernameNotFoundException.name())) {
                errorCode = ErrorCode.UsernameNotFoundException;
                setResponse(response, errorCode);
            } else if (loginException.equals(ErrorCode.AccountExpiredException.name())) {
                errorCode = ErrorCode.AccountExpiredException;
                setResponse(response, errorCode);
            } else if (loginException.equals(ErrorCode.CredentialsExpiredException.name())) {
                errorCode = ErrorCode.CredentialsExpiredException;
                setResponse(response, errorCode);
            } else if (loginException.equals(ErrorCode.DisabledException.name())) {
                errorCode = ErrorCode.DisabledException;
                setResponse(response, errorCode);
            } else if (loginException.equals(ErrorCode.LockedException.name())) {
                errorCode = ErrorCode.LockedException;
                setResponse(response, errorCode);
            } else {
                errorCode = ErrorCode.NoneException;
                setResponse(response, errorCode);
            }
        }
    }

    private void setResponse(HttpServletResponse response, ErrorCode errorCode) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        ErrorResponse errorResponse = new ErrorResponse(errorCode.getCode(), errorCode.getMessage());
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}
