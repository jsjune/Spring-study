package spring.security.jwtrefreshtoken.common.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import spring.security.jwtrefreshtoken.common.dto.ResponseDto;

@RestControllerAdvice
public class TokenControllerAdvice {

    @ExceptionHandler(value = GlobalException.class)
    public ResponseEntity<?> handleGlobalException(GlobalException e) {
        return ResponseEntity.status(e.getErrorCode().getHttpStatus())
                .body(ResponseDto.fail(e.getErrorCode().getCode(), e.getErrorCode().getMessage()));
    }
}
