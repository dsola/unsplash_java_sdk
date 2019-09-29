package com.unsplash.sdk.api;

import com.unsplash.sdk.api.v1.resources.UserProfile;

import java.util.List;

public interface UnSplashApiClient {
    String getAuthorizationUrl(List<String> scopes);
}
