package com.unsplash.sdk.exceptions;

import java.io.IOException;

public class WrongJsonUserCredentials extends IOException {
    public WrongJsonUserCredentials(String message) {
        super(message);
    }
}
