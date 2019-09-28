package com.unsplash.sdk;

import java.util.ResourceBundle;

public class ConfigProperties {

    private ResourceBundle rb;

    ConfigProperties(ResourceBundle rb) {
        this.rb = rb;
    }

    public static ConfigProperties load() {
        ResourceBundle rb = null;
        try {
            rb = ResourceBundle.getBundle("config");
        } catch (NullPointerException ex) {
            ex.printStackTrace();
            System.exit(ErrorCodes.CANNOT_LOAD_CONFIG_FILE);
        }
        return new ConfigProperties(rb);
    }

    public String getAccessKey() {
        return rb.getString("unsplash.api.access_key");
    }

    public String getApiVersion() {
        return rb.getString("unsplash.api.version");
    }
}
