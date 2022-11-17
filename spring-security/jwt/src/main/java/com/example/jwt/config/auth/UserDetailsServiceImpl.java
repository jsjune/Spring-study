package com.example.jwt.config.auth;

import com.example.jwt.model.User;
import com.example.jwt.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("UserDetailsServiceImpl의 loadUserByUsername");
        System.out.println(email);
        User user = userRepository.findByEmail(email).orElse(null);
//            .orElseThrow(() -> new UsernameNotFoundException("Can't find " + username));
        System.out.println("user = " + user);
        return new UserDetailsImpl(user);
    }
}
