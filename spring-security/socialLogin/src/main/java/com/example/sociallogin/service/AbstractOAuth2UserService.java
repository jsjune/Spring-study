package com.example.sociallogin.service;

import com.example.sociallogin.model.GoogleUser;
import com.example.sociallogin.model.KeycloakUser;
import com.example.sociallogin.model.NaverUser;
import com.example.sociallogin.model.ProviderUser;
import com.example.sociallogin.model.User;
import com.example.sociallogin.repository.UserRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@Getter
public abstract class AbstractOAuth2UserService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    public void register(ProviderUser providerUser, OAuth2UserRequest userRequest) {
        User user = userRepository.findByUsername(providerUser.getUsername());

        if (user == null) {
            ClientRegistration clientRegistration = userRequest.getClientRegistration();
            userService.register(clientRegistration.getRegistrationId(), providerUser);
        } else {
            System.out.println("userRequest = " + userRequest);
        }
    }

    public ProviderUser providerUser(ClientRegistration clientRegistration, OAuth2User oAuth2User) {

        String registrationId = clientRegistration.getRegistrationId();
        if (registrationId.equals("keycloak")) {
            return new KeycloakUser(oAuth2User, clientRegistration);

        } else if (registrationId.equals("google")) {
            return new GoogleUser(oAuth2User, clientRegistration);
        } else if (registrationId.equals("naver")) {
            return new NaverUser(oAuth2User, clientRegistration);
        }
        return null;
    }
}
