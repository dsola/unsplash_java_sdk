package com.unsplash.sdk;

import java.util.ResourceBundle;

public class ConfigProperties {

    private ResourceBundle rb;

    private ConfigProperties(ResourceBundle rb) {
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

    public String getClientId() {
        return rb.getString("unsplash.api.client_id");
    }

    public String getClientSecret() {
        return rb.getString("unsplash.api.client_secret");
    }

    public String getAuthorizationCode() {
        return rb.getString("unsplash.api.authorization_code");
    }

    public String getRedirectUri() {
        return rb.getString("unsplash.api.redirect_uri");
    }

    public String getApiVersion() {
        return rb.getString("unsplash.api.version");
    }
}
