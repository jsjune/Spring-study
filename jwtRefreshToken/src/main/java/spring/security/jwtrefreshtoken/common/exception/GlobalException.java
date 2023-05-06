package spring.security.jwtrefreshtoken.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class GlobalException extends RuntimeException{
    private final ErrorCode errorCode;

}
