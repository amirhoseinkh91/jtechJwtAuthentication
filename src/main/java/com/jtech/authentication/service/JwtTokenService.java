package com.jtech.authentication.service;

import com.jtech.authentication.exception.TokenExpiredException;
import com.jtech.authentication.model.JwtDetails;
import com.jtech.authentication.model.JwtTokenUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

/**
 * @Author amir
 * @CreatedAt 8/29/19
 */
public class JwtTokenService {

    private final String secret;

    @Autowired
    public JwtTokenService(JwtDetails jwtDetails) {
        this.secret = jwtDetails.getSecretKey();
    }


    public JwtTokenUserDetails parseToken(String token) throws TokenExpiredException {
        return JwtTokenUserDetails.builder()
                .username(getUsernameFromToken(token))
                .cellPhoneNumber(getCellPhoneNumberFromToken(token))
                .email(getEmailFromToken(token))
                .enabled(isEnabledFromToken(token))
                .firstName(getFirstNameFromToken(token))
                .lastName(getLastNameFromToken(token))
                .nickName(getNickNameFromToken(token))
                .userExpirationDate(getUserExpirationDateFromToken(token))
                .authorities(getAuthoritiesFromToken(token))
                .build();
    }


    private String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    private Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    private String getCellPhoneNumberFromToken(String token) {
        Claims claims = getAllClaimsFromToken(token);
        return (String) claims.get("cellPhone");
    }

    private String getEmailFromToken(String token) {
        Claims claims = getAllClaimsFromToken(token);
        return (String) claims.get("email");
    }

    private String getFirstNameFromToken(String token) {
        Claims claims = getAllClaimsFromToken(token);
        return (String) claims.get("firstName");
    }

    private String getLastNameFromToken(String token) {
        Claims claims = getAllClaimsFromToken(token);
        return (String) claims.get("lastName");
    }

    private String getNickNameFromToken(String token) {
        Claims claims = getAllClaimsFromToken(token);
        return (String) claims.get("nickName");
    }

    private boolean isEnabledFromToken(String token) {
        Claims claims = getAllClaimsFromToken(token);
        return (Boolean) claims.get("enabled");
    }

    private Date getUserExpirationDateFromToken(String token) {
        Claims claims = getAllClaimsFromToken(token);
        return (Date) claims.get("userExpirationDate");
    }

    private Set<String> getAuthoritiesFromToken(String token) {
        Claims claims = getAllClaimsFromToken(token);
        return (Set<String>) claims.get("authorities");
    }

    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenNotExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.after(new Date());
    }

    public Optional<Boolean> validateToken(String token) {
        return isTokenNotExpired(token) ? Optional.of(Boolean.TRUE) : Optional.empty();
    }

}
