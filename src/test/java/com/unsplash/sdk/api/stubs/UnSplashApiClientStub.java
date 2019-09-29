package com.unsplash.sdk.api.stubs;

import com.github.javafaker.Faker;
import com.unsplash.sdk.api.UnSplashApiClient;
import com.unsplash.sdk.entities.Collection;
import com.unsplash.sdk.entities.Photo;
import com.unsplash.sdk.entities.TokenCredentials;
import com.unsplash.sdk.entities.UserProfile;
import com.unsplash.sdk.errors.InvalidResponseFormat;
import com.unsplash.sdk.errors.UnSplashApiError;
import com.unsplash.sdk.exceptions.InvalidJsonFormat;
import com.unsplash.sdk.exceptions.WrongJsonUserCredentials;

import java.util.List;

public class UnSplashApiClientStub implements UnSplashApiClient {

    private final String authorizationUrl;
    private final TokenCredentials tokenCredentials;
    private final UserProfile userProfile;

    public UnSplashApiClientStub() {
        Faker faker = new Faker();
        authorizationUrl = faker.internet().url();
        tokenCredentials = new TokenCredentialsStub();
        userProfile = new UserProfileStub();
    }

    @Override
    public String generateAuthorizationUrl(List<String> scopes) {
        return authorizationUrl;
    }

    @Override
    public TokenCredentials generateAccessToken(String authorizationCode) throws WrongJsonUserCredentials, UnSplashApiError {
        return tokenCredentials;
    }

    @Override
    public UserProfile getUserProfile(String accessToken) throws UnSplashApiError, InvalidJsonFormat, InvalidResponseFormat {
        return userProfile;
    }

    @Override
    public List<Collection> getCollections(String accessToken) throws UnSplashApiError, InvalidJsonFormat, InvalidResponseFormat {
        return null;
    }

    @Override
    public List<Photo> getPhotos(String accessToken) throws UnSplashApiError, InvalidJsonFormat, InvalidResponseFormat {
        return null;
    }

    public String getAuthorizationUrl() {
        return authorizationUrl;
    }

    public TokenCredentials getTokenCredentials() {
        return tokenCredentials;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }
}
