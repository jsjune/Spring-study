package spring.security.jwtrefreshtoken.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    EXIST_EMAIL(HttpStatus.BAD_REQUEST, "USER_01", "존재하는 이메일 입니다."),
    UsernameNotFoundException(HttpStatus.UNAUTHORIZED, "USER_02", "계정이 존재하지 않습니다."),
    BadCredentialsException(HttpStatus.UNAUTHORIZED, "USER_03", "비밀번호가 불일치 합니다."),
    AccountExpiredException(HttpStatus.UNAUTHORIZED, "USER_04", "계정이 만료되었습니다."),
    CredentialsExpiredException(HttpStatus.UNAUTHORIZED, "USER_05", "비밀번호가 만료되었습니다."),
    DisabledException(HttpStatus.UNAUTHORIZED, "USER_06", "계정이 비활성화되었습니다."),
    LockedException(HttpStatus.UNAUTHORIZED, "USER_07", "계정이 잠겼습니다."),
    NoneException(HttpStatus.UNAUTHORIZED, "USER_08", "알 수 없는 에러 입니다."),
    INVALID_JWT_SIGNATURE(HttpStatus.UNAUTHORIZED, "TOKEN_01", "JWT signature 가 유효하지 않습니다."),
    INVALID_JWT_TOKEN(HttpStatus.UNAUTHORIZED, "TOKEN_02", "JWT 토큰이 유효하지 않습니다."),
    EXPIRED_ACCESS_TOKEN(HttpStatus.UNAUTHORIZED, "TOKEN_03", "토큰시간이 만료되었습니다."),
    UNSUPPORTED_JWT_TOKEN(HttpStatus.UNAUTHORIZED, "TOKEN_04", "지원하지 않는 토큰입니다."),
    CLAIMS_EMPTY(HttpStatus.UNAUTHORIZED, "TOKEN_05", "JWT claims 가 비어있습니다."),
    EXPIRED_REFRESH_TOKEN(HttpStatus.BAD_REQUEST, "TOKEN_06", "리프레시 토큰시간이 만료되었습니다. 다시 로그인 해주세요."),
    NOT_FOUND_REFRESH_TOKEN(HttpStatus.BAD_REQUEST, "TOKEN_07", "데이터베이스에 리프레시 토큰을 찾을 수 없습니다.");

    ErrorCode(HttpStatus httpStatus, String code, String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }

    private HttpStatus httpStatus;
    private String code;
    private String message;
}
