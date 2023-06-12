package spring.security.jwtrefreshtoken.config.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import spring.security.jwtrefreshtoken.config.service.UserDetailsServiceImpl;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class JwtTokenFilterTest {
    @Mock
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private JwtUtils jwtUtils;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    private JwtTokenFilter jwtTokenFilter;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        jwtTokenFilter = new JwtTokenFilter(userDetailsService, jwtUtils);
    }

    @DisplayName("유효하지 않은 토큰일때 인증 실패헀을 경우")
    @Test
    public void invalidToken_ShouldNotSetAuthentication() throws ServletException, IOException, IOException {
        // Arrange
        String invalidToken = "invalid-token";
        when(jwtUtils.parseJwtToken(request)).thenReturn(invalidToken);
        when(jwtUtils.validateJwtToken(invalidToken, request)).thenReturn(false);

        // Act
        jwtTokenFilter.doFilterInternal(request, response, filterChain);

        // Assert
        verify(userDetailsService, never()).loadUserByUsername(anyString());
        verify(filterChain).doFilter(request, response);
    }

    @DisplayName("유효한 토큰일때 인증 성공했을 경우")
    @Test
    public void validToken_ShouldSetAuthentication() throws ServletException, IOException {
        // Arrange
        String validToken = "valid-token";
        String email = "user@example.com";

        when(jwtUtils.parseJwtToken(request)).thenReturn(validToken);
        when(jwtUtils.validateJwtToken(validToken, request)).thenReturn(true);
        when(jwtUtils.getEmailFromJwtToken(validToken)).thenReturn(email);

        UserDetails userDetails = mock(UserDetails.class);
        when(userDetailsService.loadUserByUsername(email)).thenReturn(userDetails);

        // Act
        jwtTokenFilter.doFilterInternal(request, response, filterChain);

        // Assert
        verify(userDetailsService).loadUserByUsername(email);
        verify(filterChain).doFilter(request, response);

        // Verify authentication is set
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        assertThat(authentication).isInstanceOf(UsernamePasswordAuthenticationToken.class);
        assertThat(authentication.getPrincipal()).isSameAs(userDetails);
        assertThat(authentication.getCredentials()).isNull();
    }
}
