package com.example.sociallogin2.service;


import com.example.sociallogin2.converters.ProviderUserConverter;
import com.example.sociallogin2.converters.ProviderUserRequest;
import com.example.sociallogin2.model.social.GoogleUser;
import com.example.sociallogin2.model.social.KeycloakUser;
import com.example.sociallogin2.model.social.NaverUser;
import com.example.sociallogin2.model.ProviderUser;
import com.example.sociallogin2.model.users.User;
import com.example.sociallogin2.repository.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
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
    @Autowired
    private ProviderUserConverter<ProviderUserRequest, ProviderUser> providerUserConverter;

    public void register(ProviderUser providerUser, OAuth2UserRequest userRequest){
        User user = userRepository.findByUsername(providerUser.getUsername());

        if(user == null){
            ClientRegistration clientRegistration = userRequest.getClientRegistration();
            userService.register(clientRegistration.getRegistrationId(),providerUser);
        }else{
            System.out.println("userRequest = " + userRequest);
        }
    }

    public ProviderUser providerUser(ProviderUserRequest providerUserRequest){
        return providerUserConverter.converter(providerUserRequest);
    }
}
