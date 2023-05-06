package spring.security.jwtrefreshtoken.config.jwt;

import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import spring.security.jwtrefreshtoken.common.exception.ErrorCode;

import java.util.Date;

@Component
@Slf4j
public class JwtUtils {
    @Value("${jwt.app.jwtSecret}")
    private String jwtSecret;

    public String generateAccessTokenFromEmail(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + JwtProperties.ACCESS_TOKEN_EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }

    public String getEmailFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String token, HttpServletRequest request) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            log.warn("Invalid JWT signature: {}", e.getMessage());
            request.setAttribute("exception",ErrorCode.INVALID_JWT_SIGNATURE);
        } catch (MalformedJwtException e) {
            log.warn("Invalid JWT token: {}", e.getMessage());
            request.setAttribute("exception", ErrorCode.INVALID_JWT_TOKEN);
        } catch (ExpiredJwtException e) {
            log.warn("JWT token is expired: {}", e.getMessage());
            request.setAttribute("exception", ErrorCode.EXPIRED_ACCESS_TOKEN.getCode());
        } catch (UnsupportedJwtException e) {
            log.warn("JWT token is unsupported: {}", e.getMessage());
            request.setAttribute("exception",ErrorCode.UNSUPPORTED_JWT_TOKEN);
        } catch (IllegalArgumentException e) {
            log.warn("JWT claims string is empty: {}", e.getMessage());
            request.setAttribute("exception", ErrorCode.CLAIMS_EMPTY);
        }
        return false;
    }

    public String parseJwtToken(HttpServletRequest request) {
        String header = request.getHeader(JwtProperties.HEADER_STRING);
        if (StringUtils.hasText(header) && header.startsWith(JwtProperties.TOKEN_PREFIX)) {
            return header.replace(JwtProperties.TOKEN_PREFIX, "");
        }
        return null;
    }
}
