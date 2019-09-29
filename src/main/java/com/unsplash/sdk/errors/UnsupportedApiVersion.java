package com.unsplash.sdk.errors;

public class UnsupportedApiVersion extends Error {
    public UnsupportedApiVersion(String version) {
        super("The version " + version + " is not supported for the system.");
    }
}
