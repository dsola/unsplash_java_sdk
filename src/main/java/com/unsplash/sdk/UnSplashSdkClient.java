package com.unsplash.sdk;

import com.unsplash.sdk.api.UnSplashApiClient;
import com.unsplash.sdk.api.UnSplashApiClientFactory;
import com.unsplash.sdk.entities.UserCredentials;

import java.util.List;

public class UnSplashSdkClient {

    private UnSplashApiClient apiClient;

    UnSplashSdkClient(UnSplashApiClientFactory factory, String version, UserCredentials userCredentials) {
        apiClient = factory.build(version, userCredentials);
    }

    public static UnSplashSdkClient initialize(String version, UserCredentials userCredentials) {
        return new UnSplashSdkClient(new UnSplashApiClientFactory(), version, userCredentials);
    }

    public String generateAuthorizeUrl(List<String> scopes) {
        return apiClient.getAuthorizationUrl(scopes);
    }
}
