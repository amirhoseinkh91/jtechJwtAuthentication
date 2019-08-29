package com.jtech.authentication.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @Author amir
 * @CreatedAt 8/29/19
 */
public class JwtAuthenticationException extends AuthenticationException {

    public JwtAuthenticationException(String msg, Throwable t) {
        super(msg, t);
    }

    public JwtAuthenticationException(String msg) {
        this(msg, new Throwable());
    }
}
