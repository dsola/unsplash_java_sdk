package com.unsplash.sdk;

import java.util.ResourceBundle;

public class ConfigPropertiesLoader {
    public static ResourceBundle load() {
        ResourceBundle rb = null;
        try {
            rb = ResourceBundle.getBundle("config");
        } catch (NullPointerException ex) {
            ex.printStackTrace();
            System.exit(ErrorCodes.CANNOT_LOAD_CONFIG_FILE);
        }
        return rb;
    }
}
