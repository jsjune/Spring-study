package spring.security.jwtrefreshtoken.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.security.jwtrefreshtoken.dto.request.LoginRequest;
import spring.security.jwtrefreshtoken.dto.request.SignupRequest;
import spring.security.jwtrefreshtoken.dto.request.TokenRefreshRequest;
import spring.security.jwtrefreshtoken.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest request) {
        return ResponseEntity.ok(userService.registerUser(request));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(userService.login(request));
    }

    @PostMapping("/reissue")
    public ResponseEntity<?> reissue(@Valid @RequestBody TokenRefreshRequest request) {
        return ResponseEntity.ok(userService.reissue(request));
    }
}
