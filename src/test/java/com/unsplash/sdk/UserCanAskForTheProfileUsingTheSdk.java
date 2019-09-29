package com.unsplash.sdk;

import com.unsplash.sdk.api.stubs.UnSplashApiClientStub;
import com.unsplash.sdk.entities.UserProfile;
import com.unsplash.sdk.errors.AnyAccessTokenGenerated;
import com.unsplash.sdk.exceptions.WrongJsonUserCredentials;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

final class UserCanAskForTheProfileUsingTheSdk extends SdkClientTest {
    @Test
    final void the_sdk_not_allows_to_request_the_user_profile_without_access_token() {
        UnSplashApiClientStub apiClient = new UnSplashApiClientStub();
        UnSplashSdkClient client = buildSdkClientWithApiClientStub(apiClient);

        assertThrows(AnyAccessTokenGenerated.class, client::getUserProfile);
    }

    @Test
    final void the_sdk_returns_the_correct_access_token_to_the_user() throws WrongJsonUserCredentials {
        UnSplashApiClientStub apiClient = new UnSplashApiClientStub();
        UnSplashSdkClient client = buildSdkClientWithApiClientStub(apiClient);

        client.generateAccessToken("some-authorization-code");
        UserProfile userProfile = client.getUserProfile();

        assertEquals(apiClient.getUserProfile(), userProfile);
    }
}
