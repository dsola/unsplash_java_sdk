package com.unsplash.sdk.api.v1.resources;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.unsplash.sdk.entities.TokenCredentials;
import lombok.Setter;

@JsonIgnoreProperties({"created_at", "scope", "token_type"})
public class TokenV1Credentials implements TokenCredentials {
    @JsonProperty("access_token")
    @Setter
    private String accessToken;
    @JsonProperty("refresh_token")
    @Setter
    private String refreshToken;

    @Override
    public String getAccessToken() {
        return accessToken;
    }

    @Override
    public String getRefreshToken() {
        return refreshToken;
    }
}
