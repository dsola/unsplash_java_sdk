package com.unsplash.sdk.api.v1;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unsplash.sdk.api.UnSplashApiClient;
import com.unsplash.sdk.api.v1.resources.collection.CollectionV1;
import com.unsplash.sdk.api.v1.resources.TokenV1Credentials;
import com.unsplash.sdk.api.v1.resources.photo.PhotoV1;
import com.unsplash.sdk.api.v1.resources.profile.UserProfileV1;
import com.unsplash.sdk.entities.Collection;
import com.unsplash.sdk.entities.Photo;
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
import java.util.stream.Collectors;

final public class UnSplashApiV1Client implements UnSplashApiClient {

    private static final String BASE_URI = "https://api.unsplash.com";
    private static final String OAUTH_URL = "https://unsplash.com/oauth";

    private final HttpClient client;
    private final UserV1Credentials userCredentials;

    public UnSplashApiV1Client(HttpClient client, UserV1Credentials userCredentials) {
        this.client = client;
        this.userCredentials = userCredentials;
    }

    @Override
    public String generateAuthorizationUrl(List<String> scopes) {
        String authorizeUrl = OAUTH_URL + "/authorize?" + userCredentials.toUriFormat().toString() + "&response_type=code";
        if (!scopes.isEmpty()) {
            authorizeUrl += "&scope=" + String.join("+", scopes);
        }
        return authorizeUrl;
    }

    @Override
    public TokenCredentials generateAccessToken(String authorizationCode) throws WrongJsonUserCredentials, UnSplashApiError {
        JSONObject jsonCredentials = userCredentials.toRequestAccessToken(authorizationCode);

        HttpRequest request = HttpRequest.newBuilder()
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .header("Accept-Version", "1.0")
                .uri(URI.create(OAUTH_URL + "/token"))
                .POST(HttpRequest.BodyPublishers.ofString(jsonCredentials.toJSONString()))
                .build();
        HttpResponse<String> response;
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
            e.printStackTrace();
            throw new InvalidResponseFormat("The system can't find the access token in the response");
        }
    }

    @Override
    public UserProfileV1 getUserProfile(String accessToken) throws UnSplashApiError, InvalidJsonFormat, InvalidResponseFormat {
        HttpResponse<String> response;
        try {
            HttpRequest request = buildHttpGetRequest("me", accessToken);
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException|InterruptedException e) {
            System.out.println("Error when trying to get the user profile with accessToken [" + accessToken + "]");
            e.printStackTrace();
            throw new UnSplashApiError(e.getMessage());
        }
        validateResponse(response);
        return extractUserProfileFromResponse(response);
    }

    @Override
    public List<Collection> requestForCollections(String accessToken) throws UnSplashApiError, InvalidJsonFormat, InvalidResponseFormat {
        HttpResponse<String> response;
        try {
            HttpRequest request = buildHttpGetRequest("collections", accessToken);
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException|InterruptedException e) {
            System.out.println("Error when trying to get collections with accessToken [" + accessToken + "]");
            e.printStackTrace();
            throw new UnSplashApiError(e.getMessage());
        }
        validateResponse(response);
        return extractCollectionsFromResponse(response);
    }

    @Override
    public List<Photo> requestForPhotos(String accessToken) throws UnSplashApiError, InvalidJsonFormat, InvalidResponseFormat {
        HttpResponse<String> response;
        try {
            HttpRequest request = buildHttpGetRequest("photos", accessToken);
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException|InterruptedException e) {
            System.out.println("Error when trying to get photos with accessToken [" + accessToken + "]");
            e.printStackTrace();
            throw new UnSplashApiError(e.getMessage());
        }
        validateResponse(response);
        return extractPhotosFromResponse(response);
    }

    private HttpRequest buildHttpGetRequest(String endpoint, String accessToken) {
        return HttpRequest.newBuilder()
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .header("Accept-Version", "1.0")
                .header("Authorization", "Bearer " + accessToken)
                .uri(URI.create(BASE_URI + "/" + endpoint))
                .GET()
                .build();
    }

    private UserProfileV1 extractUserProfileFromResponse(HttpResponse<String> response) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Reader reader = new StringReader(response.body());
            return objectMapper.readValue(reader, UserProfileV1.class);
        } catch (NullPointerException| IOException e) {
            e.printStackTrace();
            throw new InvalidResponseFormat("The system can't find a valid user profile from the response");
        }
    }

    private List<Collection> extractCollectionsFromResponse(HttpResponse<String> response) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Reader reader = new StringReader(response.body());
            return objectMapper.readValue(reader, new TypeReference<List<CollectionV1>>(){});
        } catch (NullPointerException| IOException e) {
            e.printStackTrace();
            throw new InvalidResponseFormat("The system can't find any valid collection from the response");
        }
    }

    private List<Photo> extractPhotosFromResponse(HttpResponse<String> response) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Reader reader = new StringReader(response.body());
            return objectMapper.readValue(reader, new TypeReference<List<PhotoV1>>(){});
        } catch (NullPointerException| IOException e) {
            e.printStackTrace();
            throw new InvalidResponseFormat("The system can't find any valid photos from the response");
        }
    }

    private void validateResponse(HttpResponse<String> response) throws UnSplashApiError, InvalidJsonFormat
    {
        if (response.statusCode() >= 200 && response.statusCode() <= 301) {
            return;
        }
        JSONParser parser = new JSONParser();
        try {
            JSONObject jsonResponse = (JSONObject) parser.parse(response.body());
            throw new UnSplashApiError(buildErrorMessage(response, jsonResponse));

        } catch (ParseException e) {
            throw new InvalidJsonFormat("The response from the server has a bad format -> " + response.body());
        }
    }

    private String buildErrorMessage(HttpResponse<String> response, JSONObject jsonResponse) {
        String errorMessage = "Error response from server -> " + response.statusCode();
        if (jsonResponse.containsKey("error")) {
            errorMessage += "[" + jsonResponse.get("error") + "] " + jsonResponse.get("error_description");
        } else if (jsonResponse.containsKey("errors")) {
            List<String> errorMessageList = (List<String>) jsonResponse.get("errors");
            String errorMessages = errorMessageList.stream().map(Object::toString).collect(Collectors.joining(" && "));
            errorMessage += errorMessages;

        }
        return errorMessage;
    }
}
