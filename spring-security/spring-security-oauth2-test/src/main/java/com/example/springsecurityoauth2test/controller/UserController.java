package com.example.springsecurityoauth2test.controller;

import com.example.springsecurityoauth2test.dto.RequestLogin;
import com.example.springsecurityoauth2test.security.UserDetailsImpl;
import com.example.springsecurityoauth2test.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public String join(@RequestBody RequestLogin requestLogin) {
        return userService.join(requestLogin);
    }

    @GetMapping("/user-info")
    public UserDetailsImpl user(
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        System.out.println(userDetails);
        return userDetails;
    }
}
