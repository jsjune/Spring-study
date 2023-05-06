package spring.security.jwtrefreshtoken.config.jwt;

public interface JwtProperties {
    long ACCESS_TOKEN_EXPIRATION_TIME = 30 * 60 * 1000L; // 30분
    long REFRESH_TOKEN_EXPIRE_TIME = 7 * 24 * 60 * 60 * 1000L; // 7일
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING = "Authorization";
}
