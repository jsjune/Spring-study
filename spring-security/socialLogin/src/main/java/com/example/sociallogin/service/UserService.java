package com.example.sociallogin.service;

import com.example.sociallogin.model.ProviderUser;
import com.example.sociallogin.model.User;
import com.example.sociallogin.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void register(String registrationId, ProviderUser providerUser) {
        List<? extends GrantedAuthority> authorities = providerUser.getAuthorities();
        System.out.println("=================================================");
        System.out.println(authorities.get(0));
        System.out.println("=================================================");
        User user = User.builder()
            .registrationId(registrationId)
            .id(providerUser.getId())
            .username(providerUser.getUsername())
            .password(providerUser.getPassword())
            .email(providerUser.getEmail())
//            .authorities(providerUser.getAuthorities())
            .build();
        userRepository.save(user);
//        userRepository.register(user);
    }

}
