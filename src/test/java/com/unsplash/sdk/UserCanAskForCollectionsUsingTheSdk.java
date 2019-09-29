package com.unsplash.sdk;

import com.unsplash.sdk.api.stubs.UnSplashApiClientStub;
import com.unsplash.sdk.entities.Collection;
import com.unsplash.sdk.errors.AnyAccessTokenGenerated;
import com.unsplash.sdk.exceptions.WrongJsonUserCredentials;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserCanAskForCollectionsUsingTheSdk extends SdkClientTest {
    @Test
    final void the_sdk_not_allows_to_request_for_collections_without_access_token() {
        UnSplashApiClientStub apiClient = new UnSplashApiClientStub();
        UnSplashSdkClient client = buildSdkClientWithApiClientStub(apiClient);

        assertThrows(AnyAccessTokenGenerated.class, client::getPhotos);
    }

    @Test
    final void the_sdk_returns_the_correct_list_of_collections_to_the_user() throws WrongJsonUserCredentials {
        UnSplashApiClientStub apiClient = new UnSplashApiClientStub();
        UnSplashSdkClient client = buildSdkClientWithApiClientStub(apiClient);

        client.generateAccessToken("some-authorization-code");
        List<Collection> collections = client.getCollections();

        assertEquals(apiClient.getCollections(), collections);
    }
}
