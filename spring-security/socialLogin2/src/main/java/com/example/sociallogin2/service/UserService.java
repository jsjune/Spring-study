package com.example.sociallogin2.service;

import com.example.sociallogin2.model.ProviderUser;
import com.example.sociallogin2.model.users.User;
import com.example.sociallogin2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void register(String registrationId, ProviderUser providerUser) {
        User user = User.builder()
            .registrationId(registrationId)
            .id(providerUser.getId())
            .username(providerUser.getUsername())
            .password(providerUser.getPassword())
            .email(providerUser.getEmail())
            .authorities(providerUser.getAuthorities())
            .build();
        userRepository.register(user);
    }

}
