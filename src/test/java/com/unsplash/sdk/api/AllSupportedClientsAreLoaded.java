package com.unsplash.sdk.api;

import com.unsplash.sdk.errors.UnsupportedApiVersion;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AllSupportedClientsAreLoaded {
    @Test
    void api_client_for_v1_is_loaded() {
        UnSplashApiClient client = UnSplashApiFactory.build(ApiVersions.VERSION_1);
        assertNotNull(client);
    }

    @Test
    void the_system_notifies_the_user_when_the_version_is_not_supported() {
        assertThrows(UnsupportedApiVersion.class, () -> {
            UnSplashApiFactory.build("2");
        });
    }
}
