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

import java.util.ArrayList;
import java.util.List;

public class UnSplashApiClientStub implements UnSplashApiClient {

    private final String authorizationUrl;
    private final TokenCredentials tokenCredentials;
    private final UserProfile userProfile;
    private final List<Photo> photos;
    private final List<Collection> collections;

    public UnSplashApiClientStub() {
        Faker faker = new Faker();
        authorizationUrl = faker.internet().url();
        tokenCredentials = new TokenCredentialsStub();
        userProfile = new UserProfileStub();
        photos = new ArrayList<>();
        photos.add(new PhotoStub());
        collections = new ArrayList<>();
        collections.add(new CollectionStub());
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
    public List<Collection> requestForCollections(String accessToken) throws UnSplashApiError, InvalidJsonFormat, InvalidResponseFormat {
        return collections;
    }

    @Override
    public List<Photo> requestForPhotos(String accessToken) throws UnSplashApiError, InvalidJsonFormat, InvalidResponseFormat {
        return photos;
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

    public List<Photo> getPhotos() {
        return photos;
    }

    public List<Collection> getCollections() {
        return collections;
    }
}
