package com.unsplash.sdk.api.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unsplash.sdk.api.UnSplashApiClient;
import com.unsplash.sdk.api.v1.resources.UserProfile;
import com.unsplash.sdk.exceptions.WrongJsonUserCredentials;

import javax.ws.rs.core.HttpHeaders;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

final public class UnSplashApiV1Client implements UnSplashApiClient {

    private static final String BASE_URI = "https://api.unsplash.com";
    private static final String OAUTH_URL = "https://unsplash.com/oauth";

    private HttpClient client;
    private UserV1Credentials userCredentials;

    public UnSplashApiV1Client(HttpClient client, UserV1Credentials userCredentials) {
        this.client = client;
        this.userCredentials = userCredentials;
    }

    @Override
    public String getAuthorizationUrl(List<String> scopes) {
        return OAUTH_URL + "/authorize?" +
                userCredentials.toUriFormat().toString() +
                "&response_type=code" +
                "&scope=" + String.join("+", scopes);
    }

    public String generateAccessToken(String authorizationCode) throws WrongJsonUserCredentials {
        String jsonCredentials = userCredentials.toJson();
        HttpRequest request = HttpRequest.newBuilder()
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .header("Accept-Version", "1.0")
                .uri(URI.create(OAUTH_URL + "/token"))
                .POST(HttpRequest.BodyPublishers.ofString(jsonCredentials))
                .build();
        return "access_token";
    }

    public UserProfile getUserProfile() {
        //TODO: Check the access token is not empty
        HttpRequest request = buildHttpRequest("me");
        HttpResponse<String> response = null;

        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException|InterruptedException e) {
            // TODO: Return custom API error
            e.printStackTrace();
        }

        return extractUserProfileFromResponse(response);
    }

    private HttpRequest buildHttpRequest(String endpoint) {
        return HttpRequest.newBuilder()
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .header("Accept-Version", "1.0")
                .uri(URI.create(BASE_URI + "/" + endpoint))
                .GET()
                .build();
    }

    private UserProfile extractUserProfileFromResponse(HttpResponse<String> response) {
        UserProfile userProfile = null;
        // TODO: validate if the response contains errors message, then return error
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            System.out.println("Response body -> " + response.body());
            Reader reader = new StringReader(response.body());
            userProfile = objectMapper.readValue(reader, UserProfile.class);
        } catch (NullPointerException| IOException e) {
            // TODO: Return wrong response format error
            e.printStackTrace();
        }

        return userProfile;
    }
}
