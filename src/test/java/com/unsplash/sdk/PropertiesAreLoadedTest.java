package com.unsplash.sdk;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PropertiesAreLoadedTest {
    @Test
    void the_client_id_is_loaded() {
        ConfigProperties configProperties = ConfigProperties.load();
        assertEquals("EXAMPLE_CLIENT_ID", configProperties.getClientId());
    }

    @Test
    void the_client_secret_is_loaded() {
        ConfigProperties configProperties = ConfigProperties.load();
        assertEquals("EXAMPLE_CLIENT_SECRET", configProperties.getClientSecret());
    }

    @Test
    void the_authorization_code_is_loaded() {
        ConfigProperties configProperties = ConfigProperties.load();
        assertEquals("EXAMPLE_AUTHORIZATION_CODE", configProperties.getAuthorizationCode());
    }

    @Test
    void the_redirect_uri_is_loaded() {
        ConfigProperties configProperties = ConfigProperties.load();
        assertEquals("EXAMPLE_REDIRECT_URI", configProperties.getRedirectUri());
    }

    @Test
    void the_api_version_loaded() {
        ConfigProperties configProperties = ConfigProperties.load();
        assertEquals("1", configProperties.getApiVersion());
    }
}
