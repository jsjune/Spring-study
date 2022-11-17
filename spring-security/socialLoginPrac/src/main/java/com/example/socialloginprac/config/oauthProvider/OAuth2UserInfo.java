package com.example.socialloginprac.config.oauthProvider;

import java.util.Map;

public interface OAuth2UserInfo {

    String getProviderId();

    String getProvider();

    String getEmail();

    String getName();

}
