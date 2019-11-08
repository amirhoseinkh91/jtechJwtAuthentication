package com.jtech.authentication.internal;

import com.jtech.authentication.exception.JwtAuthenticationException;
import com.jtech.authentication.internal.JwtAuthenticatedProfile;
import com.jtech.authentication.internal.JwtAuthentication;
import com.jtech.authentication.model.JwtTokenUserDetails;
import com.jtech.authentication.service.JwtTokenService;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * @author amir
 * @since 8/29/19
 */
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private final JwtTokenService jwtService;

    @Autowired
    public JwtAuthenticationProvider(JwtTokenService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {
            String token = (String) authentication.getCredentials();
            JwtTokenUserDetails userDetails = jwtService.parseToken(token);

            return jwtService.validateToken(token)
                    .map(aBoolean -> new JwtAuthenticatedProfile(userDetails))
                    .orElseThrow(() -> new JwtAuthenticationException("JWT Token validation failed"));

        } catch (JwtException ex) {
            throw new JwtAuthenticationException("Failed to verify token");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthentication.class.equals(authentication);
    }
}
