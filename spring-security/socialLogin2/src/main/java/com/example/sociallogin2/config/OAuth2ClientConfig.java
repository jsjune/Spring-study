package com.example.sociallogin2.config;

import com.example.sociallogin2.converters.ProviderUserConverter;
import com.example.sociallogin2.converters.ProviderUserRequest;
import com.example.sociallogin2.model.ProviderUser;
import com.example.sociallogin2.service.CustomOAuth2UserService;
import com.example.sociallogin2.service.CustomOidcUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

@EnableWebSecurity
@RequiredArgsConstructor
public class OAuth2ClientConfig {

    private final CustomOAuth2UserService customOAuth2UserService;
    private final CustomOidcUserService customOidcUserService;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/static/js/**", "/static/images/**", "/static/css/**","/static/scss/**");
    }

    @Bean
    public SecurityFilterChain oauth2SecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests((requests) -> requests
            .antMatchers("/api/user")
            .access("hasAnyRole('SCOPE_profile','SCOPE_image','SCOPE_email')")
            .antMatchers("/api/oidc")
            .access("hasRole('SCOPE_openid')")
            .antMatchers("/")
            .permitAll()
            .anyRequest().authenticated());
        http.formLogin().loginPage("/login").loginProcessingUrl("/loginProc").defaultSuccessUrl("/")
            .permitAll();
        http.oauth2Login(oauth2 -> oauth2.userInfoEndpoint(
            userInfoEndpointConfig -> userInfoEndpointConfig
                .userService(customOAuth2UserService)
                .oidcUserService(customOidcUserService)));
//        http.userDetailsService(customUserDetailsService);
        http.exceptionHandling()
            .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"));
        http.logout().logoutSuccessUrl("/");
        return http.build();
    }

}
