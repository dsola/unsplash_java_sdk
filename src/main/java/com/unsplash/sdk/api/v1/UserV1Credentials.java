package com.unsplash.sdk.api.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unsplash.sdk.api.UriFormat;
import com.unsplash.sdk.entities.UserCredentials;
import com.unsplash.sdk.exceptions.WrongJsonUserCredentials;
import lombok.Setter;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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

    @Override
    public String getClientId() { return clientId; }

    @Override
    public String getClientSecret() {
        return clientSecret;
    }

    @Override
    public String getRedirectUri() {
        return redirectUri;
    }

    public UriFormat toUriFormat() {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(this, UriFormat.class);
    }

    public JSONObject toRequestAccessToken(String authorizationCode) throws WrongJsonUserCredentials {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(this);
            JSONParser parser = new JSONParser();
            JSONObject jsonCredentials = (JSONObject) parser.parse(jsonString);
            jsonCredentials.put("code", authorizationCode);
            jsonCredentials.put("grant_type", "authorization_code");
            return jsonCredentials;
        } catch (IOException | ParseException e) {
            System.out.println(
                    "Something wrong happened when trying to parse the user credentials -> " + this.toString()
            );
            throw new WrongJsonUserCredentials(e.getMessage());
        }
    }
}
