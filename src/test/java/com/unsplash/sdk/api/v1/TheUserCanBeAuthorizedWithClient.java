package com.unsplash.sdk.api.v1;

import com.unsplash.sdk.api.SupportedApiVersions;
import com.unsplash.sdk.api.UnSplashApiFactory;
import com.unsplash.sdk.api.stubs.UserCredentialsStub;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TheUserCanBeAuthorizedWithClient {
    @Test
    final void the_authorization_url_contains_all_the_parameters() {
        UserV1Credentials userV1Credentials = UserCredentialsStub.makeForV1();
        UnSplashApiV1Client client = (
            UnSplashApiV1Client)UnSplashApiFactory.build(SupportedApiVersions.VERSION_1, userV1Credentials
        );

        String authorizationUrl = client.getAuthorizationUrl(new ArrayList<>());

        assertTrue(authorizationUrl.contains("/authorize"));
        assertTrue(authorizationUrl.contains("client_id"));
        assertTrue(authorizationUrl.contains("&client_secret"));
        assertTrue(authorizationUrl.contains("&redirect_uri"));
        assertTrue(authorizationUrl.contains("&response_type"));
    }

    @Test
    final void the_authorization_url_contains_all_the_scopes() {
        ArrayList<String> scopes = new ArrayList<>();
        scopes.add("read_user");
        scopes.add("public");
        UserV1Credentials userV1Credentials = UserCredentialsStub.makeForV1();
        UnSplashApiV1Client client = (
                UnSplashApiV1Client)UnSplashApiFactory.build(SupportedApiVersions.VERSION_1, userV1Credentials
        );

        String authorizationUrl = client.getAuthorizationUrl(scopes);

        assertTrue(authorizationUrl.contains("scope=read_user+public"));

    }
}
