package com.example.springsecurityoauth2test.service;

import com.example.springsecurityoauth2test.dto.RequestLogin;
import com.example.springsecurityoauth2test.model.User;
import com.example.springsecurityoauth2test.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;

    public String join(RequestLogin requestLogin) {
        Optional<User> findUser = userRepository.findByEmail(requestLogin.getEmail());
        if (!findUser.isPresent()) {
            User user = User.builder()
                .email(requestLogin.getEmail())
                .password(bCryptPasswordEncoder.encode(requestLogin.getPassword()))
                .roles("ROLE_USER")
                .build();
            userRepository.save(user);
        }
        return "회원가입완료";
    }
}
