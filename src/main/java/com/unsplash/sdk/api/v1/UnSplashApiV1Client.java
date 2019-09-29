package com.unsplash.sdk.api.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unsplash.sdk.api.UnSplashApiClient;
import com.unsplash.sdk.api.v1.resources.TokenV1Credentials;
import com.unsplash.sdk.api.v1.resources.UserProfile;
import com.unsplash.sdk.entities.TokenCredentials;
import com.unsplash.sdk.errors.InvalidResponseFormat;
import com.unsplash.sdk.errors.UnSplashApiError;
import com.unsplash.sdk.exceptions.InvalidJsonFormat;
import com.unsplash.sdk.exceptions.WrongJsonUserCredentials;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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

    @Override
    public TokenCredentials generateAccessToken(String authorizationCode) throws WrongJsonUserCredentials, UnSplashApiError {
        JSONObject jsonCredentials = generateJsonCredentials(authorizationCode);

        HttpRequest request = HttpRequest.newBuilder()
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .header("Accept-Version", "1.0")
                .uri(URI.create(OAUTH_URL + "/token"))
                .POST(HttpRequest.BodyPublishers.ofString(jsonCredentials.toJSONString()))
                .build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException|InterruptedException e) {
            System.out.println("Error when trying to get the access token using the code: " + authorizationCode);
            throw new UnSplashApiError(e.getMessage());
        }
        this.validateResponse(response);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Reader reader = new StringReader(response.body());
            return objectMapper.readValue(reader, TokenV1Credentials.class);
        } catch (IOException e) {
            throw new InvalidResponseFormat("The system can't find the access token in the response");
        }
    }

    private JSONObject generateJsonCredentials(String authorizationCode) throws WrongJsonUserCredentials {
        JSONObject jsonCredentials = userCredentials.toJson();
        jsonCredentials.put("grant_type", "authorization_code");
        jsonCredentials.put("code", authorizationCode);
        return jsonCredentials;
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

    private void validateResponse(HttpResponse<String> response) throws UnSplashApiError, InvalidJsonFormat
    {
        if (response.statusCode() >= 200 && response.statusCode() <= 301) {
            return;
        }
        JSONParser parser = new JSONParser();
        try {
            JSONObject jsonResponse = (JSONObject) parser.parse(response.body());
            if (jsonResponse.containsKey("error")) {
                String errorMessage = "Error response from server -> " +
                        response.statusCode() +
                        "[" + jsonResponse.get("error") + "] " +
                        jsonResponse.get("error_description");
                throw new UnSplashApiError(errorMessage);
            }
        } catch (ParseException e) {
            throw new InvalidJsonFormat("The response from the server has a bad format -> " + response.body());
        }
    }
}
