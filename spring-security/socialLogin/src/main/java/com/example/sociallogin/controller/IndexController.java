package com.example.sociallogin.controller;

import java.util.Map;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(Model model, Authentication authentication,  @AuthenticationPrincipal OAuth2User oAuth2User){
        OAuth2AuthenticationToken authenticationToken = (OAuth2AuthenticationToken)authentication;
        if(authenticationToken != null){
            Map<String, Object> attributes = oAuth2User.getAttributes();
            String userName = (String)attributes.get("name");
            if(authenticationToken.getAuthorizedClientRegistrationId().equals("naver")){
                Map<String, Object> response = (Map)attributes.get("response");
                userName = (String)response.get("name");
            }
            model.addAttribute("user", userName);
        }
        System.out.println(authentication);
        return "index";
    }
}
