//package io.security.oauth2.springsecurityoauth2.practice;
//
//import org.springframework.security.oauth2.client.registration.ClientRegistration;
//import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
//import org.springframework.security.oauth2.client.registration.ClientRegistrations;
//import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
//
////@Configuration
//public class OAuth2ClientConfig01 {
////    @Bean
//    public ClientRegistrationRepository clientRegistrationRepository() {
//        return new InMemoryClientRegistrationRepository(keycloakClientRegistration());
//    }
//
//    private ClientRegistration keycloakClientRegistration() {
//
//        return ClientRegistrations.fromIssuerLocation("http://localhost:8080/realms/oauth2")
//                .registrationId("keycloak")
//                .clientId("oauth2-client-app")
//                .clientSecret("9GrHcH6UbfMDEw3Zj38uGsYgRLDgZ3pP")
//                .redirectUri("http://localhost:8081/login/oauth2/code/keycloak")
//                .build();
//    }
//
//}
