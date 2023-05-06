package spring.security.jwtrefreshtoken.common.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TokenControllerAdvice {

    @ExceptionHandler(value = GlobalException.class)
    public ResponseEntity<?> handleGlobalException(GlobalException e) {
        return ResponseEntity.status(e.getErrorCode().getHttpStatus())
                .body(new ErrorResponse(e.getErrorCode().getCode(), e.getErrorCode().getMessage()));
    }
}
