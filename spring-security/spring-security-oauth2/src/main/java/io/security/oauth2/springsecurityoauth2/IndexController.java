package io.security.oauth2.springsecurityoauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.oidc.IdTokenClaimNames;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestController
public class IndexController {
//    @Autowired
//    private ClientRegistrationRepository clientRegistrationRepository;
//
//    @GetMapping("/")
//    public String index() {
//
//        ClientRegistration keycloak = clientRegistrationRepository.findByRegistrationId("keycloak");
//
//        String clientId = keycloak.getClientId();
//        System.out.println("clientId = " + clientId);
//
//        String redirectUri = keycloak.getRedirectUri();
//        System.out.println("redirectUri = " + redirectUri);
//
//        return "index";
//    }
//
//    @GetMapping("/user")
//    public OAuth2User user(String accessToken) {
//        ClientRegistration clientRegistration = clientRegistrationRepository.findByRegistrationId("keycloak");
//        OAuth2AccessToken oAuth2AccessToken = new OAuth2AccessToken(OAuth2AccessToken.TokenType.BEARER, accessToken, Instant.now(), Instant.MAX);
//
//        OAuth2UserRequest oAuth2UserRequest = new OAuth2UserRequest(clientRegistration, oAuth2AccessToken);
//        DefaultOAuth2UserService defaultOAuth2UserService = new DefaultOAuth2UserService();
//        OAuth2User oAuth2User = defaultOAuth2UserService.loadUser(oAuth2UserRequest);
//        return oAuth2User;
//    }
//
//    @GetMapping("/oidc")
//    public OAuth2User oidc(String accessToken, String idToken) {
//        ClientRegistration clientRegistration = clientRegistrationRepository.findByRegistrationId("keycloak");
//        OAuth2AccessToken oAuth2AccessToken = new OAuth2AccessToken(OAuth2AccessToken.TokenType.BEARER, accessToken, Instant.now(), Instant.MAX);
//
//        Map<String, Object> idTokenClaims = new HashMap<>();
//        idTokenClaims.put(IdTokenClaimNames.ISS, "http://localhost:8080/realms/oauth2");
//        idTokenClaims.put(IdTokenClaimNames.SUB, "OIDC0");
//        idTokenClaims.put("preferred_username", "user");
//
//        OidcIdToken oidcIDToken = new OidcIdToken(idToken, Instant.now(), Instant.MAX, idTokenClaims);
//
//        OidcUserRequest oAuth2UserRequest = new OidcUserRequest(clientRegistration, oAuth2AccessToken, oidcIDToken);
//        OidcUserService oidcUserService = new OidcUserService();
//        OAuth2User oAuth2User = oidcUserService.loadUser(oAuth2UserRequest);
//        return oAuth2User;
//    }
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/user")
    public OAuth2User user(Authentication authentication) {
//        OAuth2AuthenticationToken authentication1 = (OAuth2AuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthenticationToken authentication2 = (OAuth2AuthenticationToken) authentication;
        OAuth2User oAuth2User = authentication2.getPrincipal();
        return oAuth2User;
    }

    @GetMapping("/oauth2User")
    public OAuth2User oAuth2User(@AuthenticationPrincipal OAuth2User oAuth2User) {
        System.out.println("oAuth2User = " + oAuth2User);
        return oAuth2User;
    }

    @GetMapping("/oidcUser")
    public OidcUser oidcUser(@AuthenticationPrincipal OidcUser oidcUser) {
        System.out.println("oidcUser = " + oidcUser);
        return oidcUser;
    }
}
