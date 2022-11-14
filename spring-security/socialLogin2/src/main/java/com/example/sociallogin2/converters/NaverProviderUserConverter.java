package com.example.sociallogin2.converters;

import com.example.sociallogin2.enums.OAuth2Config;
import com.example.sociallogin2.model.ProviderUser;
import com.example.sociallogin2.model.social.GoogleUser;
import com.example.sociallogin2.model.social.NaverUser;
import com.example.sociallogin2.util.OAuth2Utils;

public class NaverProviderUserConverter implements ProviderUserConverter<ProviderUserRequest, ProviderUser>{

    @Override
    public ProviderUser converter(ProviderUserRequest providerUserRequest) {
        if (!providerUserRequest.clientRegistration().getRegistrationId().equals(OAuth2Config.SocialType.NAVER.getSocialName())) {
            return null;
        }

        return new NaverUser(OAuth2Utils.getSubAttributes(providerUserRequest.oAuth2User(), "response"),
            providerUserRequest.oAuth2User(),
            providerUserRequest.clientRegistration());
    }
}
