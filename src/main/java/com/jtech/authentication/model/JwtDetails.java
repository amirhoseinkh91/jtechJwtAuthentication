package com.jtech.authentication.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @Author amir
 * @CreatedAt 8/29/19
 */
@Getter
@RequiredArgsConstructor
public class JwtDetails {

    private final String secretKey;
    private final String tokenHeaderName;


}
