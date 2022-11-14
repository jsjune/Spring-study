package com.example.sociallogin2.model.social;

import com.example.sociallogin2.model.Attributes;
import com.example.sociallogin2.model.OAuth2ProviderUser;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class GoogleUser extends OAuth2ProviderUser {

    public GoogleUser(Attributes mainAttributes,
        OAuth2User oAuth2User, ClientRegistration clientRegistration){
        super(mainAttributes.getMainAttributes(), oAuth2User, clientRegistration);
    }

    @Override
    public String getId() {
        return (String)getAttributes().get("sub");
    }

    @Override
    public String getUsername() {
        return (String)getAttributes().get("sub");
    }
}
