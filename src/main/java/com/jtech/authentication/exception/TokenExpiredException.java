package com.jtech.authentication.exception;

/**
 * @Author amir
 * @CreatedAt 8/29/19
 */
public class TokenExpiredException extends RuntimeException {

    public TokenExpiredException(String msg) {
        super(msg);
    }

}
