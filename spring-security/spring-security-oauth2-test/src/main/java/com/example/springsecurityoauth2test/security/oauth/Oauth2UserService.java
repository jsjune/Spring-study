package com.example.springsecurityoauth2test.security.oauth;

import com.example.springsecurityoauth2test.model.User;
import com.example.springsecurityoauth2test.repository.UserRepository;
import com.example.springsecurityoauth2test.security.UserDetailsImpl;
import com.example.springsecurityoauth2test.security.oauth.provider.GoogleUserInfo;
import com.example.springsecurityoauth2test.security.oauth.provider.NaverUserInfo;
import com.example.springsecurityoauth2test.security.oauth.provider.OAuth2UserInfo;

import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class Oauth2UserService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        return processOAuth2User(userRequest, oAuth2User);
    }

    private OAuth2User processOAuth2User(OAuth2UserRequest userRequest, OAuth2User oAuth2User) {
        OAuth2UserInfo oAuth2UserInfo = null;
        String provide = userRequest.getClientRegistration().getRegistrationId();
        if (provide.equals("google")) {
            log.info("구글 로그인 요청!!");
            oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
        } else if (provide.equals("naver")) {
            log.info("네이버 로그인 요청!!");
            oAuth2UserInfo = new NaverUserInfo(oAuth2User.getAttributes());
        }

        Optional<User> userOptional = userRepository.findByProviderAndProviderId(
            oAuth2UserInfo.getProvider(),
            oAuth2UserInfo.getProviderId());

        User user;
        if (userOptional.isPresent()) {
            user = userOptional.get();
            user.setEmail(oAuth2UserInfo.getEmail());
            userRepository.save(user);
        }else{
            user = User.builder()
                .username(oAuth2UserInfo.getUsername())
                .email(oAuth2UserInfo.getEmail())
                .roles("ROLE_USER")
                .provider(oAuth2UserInfo.getProvider())
                .providerId(oAuth2UserInfo.getProviderId())
                .build();

            userRepository.save(user);
        }
        return new UserDetailsImpl(user, oAuth2User.getAttributes());
    }
}
