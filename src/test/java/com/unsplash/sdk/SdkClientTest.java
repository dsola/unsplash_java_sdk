package com.unsplash.sdk;

import com.unsplash.sdk.api.SupportedApiVersions;
import com.unsplash.sdk.api.UnSplashApiClient;
import com.unsplash.sdk.api.UnSplashApiClientFactory;
import com.unsplash.sdk.api.stubs.UserCredentialsStub;
import com.unsplash.sdk.entities.UserCredentials;
import org.mockito.Mockito;

abstract public class SdkClientTest {
    UnSplashSdkClient buildSdkClientWithApiClientStub(UnSplashApiClient apiClient) {
        UserCredentials userCredentials = UserCredentialsStub.makeForV1();
        String version = SupportedApiVersions.VERSION_1;
        UnSplashApiClientFactory factory = Mockito.mock(UnSplashApiClientFactory.class);
        Mockito
                .when(factory.build(version, userCredentials))
                .thenReturn(apiClient);
        return new UnSplashSdkClient(factory, SupportedApiVersions.VERSION_1, userCredentials);
    }
}
