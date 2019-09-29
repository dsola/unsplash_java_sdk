package com.unsplash.sdk.api;

import com.unsplash.sdk.api.v1.resources.UserProfile;
import com.unsplash.sdk.entities.TokenCredentials;
import com.unsplash.sdk.errors.UnSplashApiError;
import com.unsplash.sdk.exceptions.WrongJsonUserCredentials;

import java.util.List;

public interface UnSplashApiClient {
    String getAuthorizationUrl(List<String> scopes);

    TokenCredentials generateAccessToken(String authorizationCode) throws WrongJsonUserCredentials, UnSplashApiError;
}
