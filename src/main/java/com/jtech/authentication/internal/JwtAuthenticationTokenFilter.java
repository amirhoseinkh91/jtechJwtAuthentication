package com.jtech.authentication.internal;

import com.jtech.authentication.internal.JwtAuthentication;
import com.jtech.authentication.model.JwtDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author amir
 * @CreatedAt 8/29/19
 */
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private String tokenHeader;

    public JwtAuthenticationTokenFilter(JwtDetails jwtDetails) {
        this.tokenHeader = jwtDetails.getTokenHeaderName();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        final String requestHeader = request.getHeader(this.tokenHeader);

        if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
            String authToken = requestHeader.substring(7);
            JwtAuthentication authentication = new JwtAuthentication(authToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request, response);

    }
}
