package spring.security.jwtrefreshtoken.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import spring.security.jwtrefreshtoken.common.exception.GlobalException;
import spring.security.jwtrefreshtoken.config.jwt.JwtUtils;
import spring.security.jwtrefreshtoken.domain.RefreshToken;
import spring.security.jwtrefreshtoken.domain.User;
import spring.security.jwtrefreshtoken.dto.request.LoginRequest;
import spring.security.jwtrefreshtoken.dto.request.SignupRequest;
import spring.security.jwtrefreshtoken.dto.request.TokenRefreshRequest;
import spring.security.jwtrefreshtoken.dto.response.UserResponseDto;
import spring.security.jwtrefreshtoken.repository.UserRepository;
import spring.security.jwtrefreshtoken.repository.redis.RefreshTokenRepository;

import java.time.Instant;
import java.util.UUID;

import static spring.security.jwtrefreshtoken.common.exception.ErrorCode.*;
import static spring.security.jwtrefreshtoken.config.jwt.JwtProperties.REFRESH_TOKEN_EXPIRE_TIME;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public Object registerUser(SignupRequest signupRequest) {
        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            throw new GlobalException(EXIST_EMAIL);
        }
        User user = User.register()
                .email(signupRequest.getEmail())
                .username(signupRequest.getUsername())
                .password(bCryptPasswordEncoder.encode(signupRequest.getPassword()))
                .roles("ROLE_USER")
                .build();
        userRepository.save(user);
        return "success";
    }

    public Object login(LoginRequest request) {
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword());
        Authentication authentication = authenticationManager.authenticate(authRequest);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String accessToken = jwtUtils.generateAccessTokenFromEmail(request.getEmail());
        RefreshToken refreshToken = RefreshToken.builder()
                .email(request.getEmail())
                .expiryDate(Instant.now().plusMillis(REFRESH_TOKEN_EXPIRE_TIME))
                .refreshToken(UUID.randomUUID().toString())
                .build();
        refreshTokenRepository.save(refreshToken);

        return ResponseEntity.ok(new UserResponseDto.TokenInfo(
                refreshToken.getEmail(),
                accessToken,
                refreshToken.getRefreshToken()));
    }

    public Object reissue(TokenRefreshRequest request) {
        String refreshToken = request.getRefreshToken();
        RefreshToken findRefreshToken = refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new GlobalException(NOT_FOUND_REFRESH_TOKEN));
        if (findRefreshToken.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(findRefreshToken);
            throw new GlobalException(EXPIRED_REFRESH_TOKEN);
        }
        String accessToken = jwtUtils.generateAccessTokenFromEmail(findRefreshToken.getEmail());
        return ResponseEntity.ok(new UserResponseDto.TokenInfo(
                findRefreshToken.getEmail(),
                accessToken,
                refreshToken));
    }
}
