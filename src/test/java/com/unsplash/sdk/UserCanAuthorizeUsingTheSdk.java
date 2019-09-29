package com.unsplash.sdk;

import com.unsplash.sdk.api.SupportedApiVersions;
import com.unsplash.sdk.api.UnSplashApiClient;
import com.unsplash.sdk.api.UnSplashApiClientFactory;
import com.unsplash.sdk.api.stubs.UnSplashApiClientStub;
import com.unsplash.sdk.api.stubs.UserCredentialsStub;
import com.unsplash.sdk.entities.TokenCredentials;
import com.unsplash.sdk.entities.UserCredentials;
import com.unsplash.sdk.exceptions.WrongJsonUserCredentials;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class UserCanAuthorizeUsingTheSdk {
    @Test
    final void user_can_initialize_the_sdk_with_the_credentials() {
        UserCredentials userCredentials = UserCredentialsStub.makeForV1();
        UnSplashSdkClient client = UnSplashSdkClient.initialize(SupportedApiVersions.VERSION_1, userCredentials);

        String authorizeUrl = client.generateAuthorizeUrl(new ArrayList<>());

        assertFalse(authorizeUrl.isEmpty());
    }

    @Test
    final void the_sdk_returns_the_same_authorization_url_than_the_api() {
        UnSplashApiClientStub apiClient = new UnSplashApiClientStub();
        UnSplashSdkClient client = buildSdkClientWithApiClientStub(apiClient);

        String authorizeUrl = client.generateAuthorizeUrl(new ArrayList<>());

        assertEquals(apiClient.getAuthorizationUrl(), authorizeUrl);
    }

    @Test
    final void the_sdk_returns_the_correct_access_token_to_the_user() throws WrongJsonUserCredentials {
        UnSplashApiClientStub apiClient = new UnSplashApiClientStub();
        UnSplashSdkClient client = buildSdkClientWithApiClientStub(apiClient);

        TokenCredentials tokenCredentials = client.generateAccessToken("some-random-code");
        
        assertEquals(apiClient.getTokenCredentials(), tokenCredentials);
    }


    private UnSplashSdkClient buildSdkClientWithApiClientStub(UnSplashApiClient apiClient) {
        UserCredentials userCredentials = UserCredentialsStub.makeForV1();
        String version = SupportedApiVersions.VERSION_1;
        UnSplashApiClientFactory factory = Mockito.mock(UnSplashApiClientFactory.class);
        Mockito
            .when(factory.build(version, userCredentials))
            .thenReturn(apiClient);
        return new UnSplashSdkClient(factory, SupportedApiVersions.VERSION_1, userCredentials);
    }
}
