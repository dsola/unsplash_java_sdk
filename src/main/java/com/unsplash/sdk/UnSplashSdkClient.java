package com.unsplash.sdk;

import com.unsplash.sdk.api.UnSplashApiClient;
import com.unsplash.sdk.api.UnSplashApiClientFactory;
import com.unsplash.sdk.entities.*;
import com.unsplash.sdk.errors.AnyAccessTokenGenerated;
import com.unsplash.sdk.errors.InvalidResponseFormat;
import com.unsplash.sdk.errors.UnSplashApiError;
import com.unsplash.sdk.exceptions.InvalidJsonFormat;
import com.unsplash.sdk.exceptions.WrongJsonUserCredentials;

import java.util.List;
import java.util.Objects;

public class UnSplashSdkClient implements UnSplashClient {

    private final UnSplashApiClient apiClient;
    private TokenCredentials tokenCredentials = null;

    UnSplashSdkClient(UnSplashApiClientFactory factory, String version, UserCredentials userCredentials) {
        apiClient = factory.build(version, userCredentials);
    }

    public static UnSplashSdkClient initialize(String version, UserCredentials userCredentials) {
        return new UnSplashSdkClient(new UnSplashApiClientFactory(), version, userCredentials);
    }

    @Override
    public String generateAuthorizeUrl(List<String> scopes) {
        return apiClient.generateAuthorizationUrl(scopes);
    }

    @Override
    public TokenCredentials generateAccessToken(String authorizationCode) throws WrongJsonUserCredentials {
        tokenCredentials = this.apiClient.generateAccessToken(authorizationCode);
        return tokenCredentials;
    }

    @Override
    public UserProfile getUserProfile() throws UnSplashApiError, InvalidJsonFormat, InvalidResponseFormat {
        validateTokenCredentials();
        return apiClient.getUserProfile(tokenCredentials.getAccessToken());
    }

    @Override
    public List<Collection> getCollections() throws UnSplashApiError, InvalidJsonFormat, InvalidResponseFormat {
        validateTokenCredentials();
        return apiClient.requestForCollections(tokenCredentials.getAccessToken());
    }

    @Override
    public List<Photo> getPhotos() throws UnSplashApiError, InvalidJsonFormat, InvalidResponseFormat {
        validateTokenCredentials();
        return apiClient.requestForPhotos(tokenCredentials.getAccessToken());
    }

    private void validateTokenCredentials() {
        if (Objects.isNull(tokenCredentials)) {
            throw new AnyAccessTokenGenerated("Any access token has been generated. Please generate an access token to request this information");
        }
    }


}
