package com.example.sociallogin2.converters;

import com.example.sociallogin2.enums.OAuth2Config;
import com.example.sociallogin2.model.ProviderUser;
import com.example.sociallogin2.model.social.GoogleUser;
import com.example.sociallogin2.util.OAuth2Utils;

public class GoogleProviderUserConverter implements ProviderUserConverter<ProviderUserRequest, ProviderUser>{

    @Override
    public ProviderUser converter(ProviderUserRequest providerUserRequest) {
        if (!providerUserRequest.clientRegistration().getRegistrationId().equals(OAuth2Config.SocialType.GOOGLE.getSocialName())) {
            return null;
        }

        return new GoogleUser(OAuth2Utils.getMainAttributes(
            providerUserRequest.oAuth2User()),
            providerUserRequest.oAuth2User(),
            providerUserRequest.clientRegistration());
    }
}
