package com.unsplash.sdk.api;

import com.unsplash.sdk.ConfigProperties;
import com.unsplash.sdk.api.v1.UnSplashApiV1Client;
import com.unsplash.sdk.errors.UnsupportedApiVersion;

import java.net.http.HttpClient;

public class UnSplashApiFactory {
    public static UnSplashApiClient build(String version) throws UnsupportedApiVersion {
        if (version.equals(ApiVersions.VERSION_1)) {
            ConfigProperties configProperties = ConfigProperties.load();
            UserCredentials userCredentials = new UserCredentials(
                    configProperties.getClientId(),
                    configProperties.getClientSecret(),
                    configProperties.getAuthorizationCode(),
                    configProperties.getRedirectUri()
            );
            HttpClient client = HttpClient.newHttpClient();

            return new UnSplashApiV1Client(client, userCredentials);
        }

        throw new UnsupportedApiVersion(version);
    }
}
