package com.unsplash.sdk.api;

import com.unsplash.sdk.api.UnSplashApiV1Client;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertTrue;


class AccountInformationIsAuthorizedTest {

    private final UnSplashApiV1Client client = new UnSplashApiV1Client();

    @Test
    void the_client_is_loaded() {
        assertTrue(Objects.nonNull(client));
    }
}
