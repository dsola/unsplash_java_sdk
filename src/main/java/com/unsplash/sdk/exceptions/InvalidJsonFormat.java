package com.unsplash.sdk.exceptions;

public class InvalidJsonFormat extends IllegalArgumentException {
    public InvalidJsonFormat(String message) {
        super(message);
    }
}
