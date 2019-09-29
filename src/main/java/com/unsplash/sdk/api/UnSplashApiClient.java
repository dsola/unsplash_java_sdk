package com.unsplash.sdk.api;

import com.unsplash.sdk.entities.TokenCredentials;
import com.unsplash.sdk.entities.UserProfile;
import com.unsplash.sdk.errors.InvalidResponseFormat;
import com.unsplash.sdk.errors.UnSplashApiError;
import com.unsplash.sdk.exceptions.InvalidJsonFormat;
import com.unsplash.sdk.exceptions.WrongJsonUserCredentials;

import java.util.List;

public interface UnSplashApiClient {
    String getAuthorizationUrl(List<String> scopes);
    TokenCredentials generateAccessToken(String authorizationCode) throws WrongJsonUserCredentials, UnSplashApiError;
    UserProfile getUserProfile(String accessToken) throws UnSplashApiError, InvalidJsonFormat, InvalidResponseFormat;
}
