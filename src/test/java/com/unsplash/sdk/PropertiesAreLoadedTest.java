package com.unsplash.sdk;

import org.junit.jupiter.api.Test;

import java.util.Properties;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PropertiesAreLoadedTest {
    @Test
    void the_secret_key_is_loaded() {
        ResourceBundle rb = ConfigPropertiesLoader.load();
        assertEquals("AC-1234", rb.getString("unsplash.api.access_key"));
    }

    @Test
    void the_api_version_loaded() {
        ResourceBundle rb = ConfigPropertiesLoader.load();
        assertEquals("1", rb.getString("unsplash.api.version"));
    }
}
