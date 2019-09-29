package com.unsplash.sdk.api.stubs;

import com.github.javafaker.Faker;
import com.unsplash.sdk.entities.TokenCredentials;

public class TokenCredentialsStub implements TokenCredentials {

    private String accessToken;

    TokenCredentialsStub() {
        Faker faker = new Faker();
        accessToken = faker.code().asin();
    }

    @Override
    public String getAccessToken() {
        return accessToken;
    }

    @Override
    public String getRefreshToken() {
        return null;
    }
}
