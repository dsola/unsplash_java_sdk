package com.unsplash.sdk;

import org.junit.jupiter.api.Test;

import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PropertiesAreLoadedTest {
    @Test
    void the_secret_key_is_loaded() {
        ConfigProperties configProperties = ConfigProperties.load();
        assertEquals("AC-1234", configProperties.getAccessKey());
    }

    @Test
    void the_api_version_loaded() {
        ConfigProperties configProperties = ConfigProperties.load();
        assertEquals("1", configProperties.getApiVersion());
    }
}
