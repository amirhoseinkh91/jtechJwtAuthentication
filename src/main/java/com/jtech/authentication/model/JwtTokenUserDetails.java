package com.jtech.authentication.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

/**
 * @Author amir
 * @CreatedAt 8/29/19
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JwtTokenUserDetails {

    private String username;
    private String firstName;
    private String lastName;
    private String nickName;
    private Date userExpirationDate;
    private boolean enabled;
    private String email;
    private String cellPhoneNumber;
    private Set<String> authorities;

    public static JwtTokenUserDetailsBuilder builder() {
        return new JwtTokenUserDetailsBuilder();
    }

    public static class JwtTokenUserDetailsBuilder {

        private JwtTokenUserDetails userDetails = new JwtTokenUserDetails();

        public JwtTokenUserDetailsBuilder username(String s) {
            userDetails.username = s;
            return this;
        }

        public JwtTokenUserDetailsBuilder firstName(String s) {
            userDetails.firstName = s;
            return this;
        }

        public JwtTokenUserDetailsBuilder lastName(String s) {
            userDetails.lastName = s;
            return this;
        }

        public JwtTokenUserDetailsBuilder nickName(String s) {
            userDetails.nickName = s;
            return this;
        }

        public JwtTokenUserDetailsBuilder userExpirationDate(Date d) {
            userDetails.userExpirationDate = d;
            return this;
        }

        public JwtTokenUserDetailsBuilder enabled(boolean b) {
            userDetails.enabled = b;
            return this;
        }

        public JwtTokenUserDetailsBuilder email(String s) {
            userDetails.email = s;
            return this;
        }

        public JwtTokenUserDetailsBuilder cellPhoneNumber(String s) {
            userDetails.cellPhoneNumber = s;
            return this;
        }

        public JwtTokenUserDetailsBuilder authorities(Set<String> s) {
            userDetails.authorities = s;
            return this;
        }

        public JwtTokenUserDetails build() {
            return userDetails;
        }

    }

}
