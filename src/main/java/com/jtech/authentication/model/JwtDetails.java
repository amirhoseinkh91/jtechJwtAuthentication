package com.jtech.authentication.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author amir
 * @since 8/29/19
 */
@Getter
@RequiredArgsConstructor
public class JwtDetails {

    private final String secretKey;
    private final String tokenHeaderName;


}
