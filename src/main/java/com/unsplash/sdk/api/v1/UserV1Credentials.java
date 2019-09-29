package com.unsplash.sdk.api.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unsplash.sdk.api.UriFormat;
import com.unsplash.sdk.entities.UserCredentials;
import com.unsplash.sdk.exceptions.WrongJsonUserCredentials;
import lombok.Setter;

import java.io.IOException;

final public class UserV1Credentials implements UserCredentials {

    @JsonProperty("client_id")
    @Setter
    private String clientId;
    @JsonProperty("client_secret")
    @Setter
    private String clientSecret;
    @JsonProperty("redirect_uri")
    @Setter
    private String redirectUri;

    public UserV1Credentials(String clientId, String clientSecret, String redirectUri) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.redirectUri = redirectUri;
    }

    public String getClientId() { return clientId; }

    public String getClientSecret() {
        return clientSecret;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public UriFormat toUriFormat() {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(this, UriFormat.class);
    }

    public String toJson() throws WrongJsonUserCredentials {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(this);
        } catch (IOException e) {
            System.out.println(
                    "Something wrong happened when trying to parse the user credentials -> " + this.toString()
            );
            throw new WrongJsonUserCredentials(e.getMessage());
        }
    }
}
