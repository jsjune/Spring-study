package spring.security.jwtrefreshtoken.common.dto;

import lombok.Getter;

@Getter
public class ResponseDto <T>{
    private boolean success;
    private T data;
    private Error error;

    public ResponseDto(boolean success, T data, Error error) {
        this.success = success;
        this.data = data;
        this.error = error;
    }

    public static <T> ResponseDto<T> success(T data) {
        return new ResponseDto<>(true, data, null);
    }

    public static <T> ResponseDto<T> fail(String code, String message) {
        return new ResponseDto<>(false, null, new Error(code, message));
    }

    @Getter
    static class Error{
        private String code;
        private String message;

        public Error(String code, String message) {
            this.code = code;
            this.message = message;
        }
    }
}
