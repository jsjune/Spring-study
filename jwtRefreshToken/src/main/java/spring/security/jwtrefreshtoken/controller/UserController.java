package spring.security.jwtrefreshtoken.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.security.jwtrefreshtoken.common.dto.ResponseDto;
import spring.security.jwtrefreshtoken.dto.request.LoginRequest;
import spring.security.jwtrefreshtoken.dto.request.SignupRequest;
import spring.security.jwtrefreshtoken.dto.request.TokenRefreshRequest;
import spring.security.jwtrefreshtoken.dto.response.UserResponseDto;
import spring.security.jwtrefreshtoken.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseDto<String> registerUser(@Valid @RequestBody SignupRequest request) {
        return ResponseDto.success(userService.registerUser(request));
    }

    @PostMapping("/login")
    public ResponseDto<UserResponseDto.TokenInfo> login(@Valid @RequestBody LoginRequest request) {
        return ResponseDto.success(userService.login(request));
    }

    @PostMapping("/reissue")
    public ResponseDto<UserResponseDto.TokenInfo> reissue(@Valid @RequestBody TokenRefreshRequest request) {
        return ResponseDto.success(userService.reissue(request));
    }
}
