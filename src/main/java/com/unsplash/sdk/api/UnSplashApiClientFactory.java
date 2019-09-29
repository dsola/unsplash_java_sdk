package com.unsplash.sdk.api;

import com.unsplash.sdk.api.v1.UnSplashApiV1Client;
import com.unsplash.sdk.api.v1.UserV1Credentials;
import com.unsplash.sdk.entities.UserCredentials;
import com.unsplash.sdk.errors.UnsupportedApiVersion;

import java.net.http.HttpClient;

public class UnSplashApiClientFactory {

    public UnSplashApiClientFactory() {

    }

    public UnSplashApiClient build(String version, UserCredentials userCredentials) throws UnsupportedApiVersion {
        if (version.equals(SupportedApiVersions.VERSION_1)) {
            HttpClient client = HttpClient.newHttpClient();
            return new UnSplashApiV1Client(client, (UserV1Credentials) userCredentials);
        }
        throw new UnsupportedApiVersion(version);
    }
}
