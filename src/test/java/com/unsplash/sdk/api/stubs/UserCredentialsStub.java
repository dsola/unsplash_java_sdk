package com.unsplash.sdk.api.stubs;

import com.github.javafaker.Faker;
import com.unsplash.sdk.api.v1.UserV1Credentials;

public class UserCredentialsStub {
    public static UserV1Credentials makeForV1() {
        Faker faker = new Faker();
        return new UserV1Credentials(faker.code().toString(), faker.code().toString(), faker.internet().url());
    }
}
