package com.unsplash.sdk.api;

import com.unsplash.sdk.entities.Collection;
import com.unsplash.sdk.entities.Photo;
import com.unsplash.sdk.entities.TokenCredentials;
import com.unsplash.sdk.entities.UserProfile;
import com.unsplash.sdk.errors.InvalidResponseFormat;
import com.unsplash.sdk.errors.UnSplashApiError;
import com.unsplash.sdk.exceptions.InvalidJsonFormat;
import com.unsplash.sdk.exceptions.WrongJsonUserCredentials;

import java.util.List;

public interface UnSplashApiClient {

    String generateAuthorizationUrl(List<String> scopes);

    TokenCredentials generateAccessToken(String authorizationCode) throws WrongJsonUserCredentials, UnSplashApiError;

    UserProfile getUserProfile(String accessToken) throws UnSplashApiError, InvalidJsonFormat, InvalidResponseFormat;

    List<Collection> getCollections(String accessToken) throws UnSplashApiError, InvalidJsonFormat, InvalidResponseFormat;

    List<Photo> getPhotos(String accessToken) throws UnSplashApiError, InvalidJsonFormat, InvalidResponseFormat;
}
