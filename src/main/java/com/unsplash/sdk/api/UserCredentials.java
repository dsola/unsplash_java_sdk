package com.unsplash.sdk.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class UserCredentials {
    private String clientId;
    private String clientSecret;
    private String authorizationCode;
    private String redirectUri;

    UserCredentials(String clientId, String clientSecret, String authorizationCode, String redirectUri) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.authorizationCode = authorizationCode;
        this.redirectUri = redirectUri;
    }
}
