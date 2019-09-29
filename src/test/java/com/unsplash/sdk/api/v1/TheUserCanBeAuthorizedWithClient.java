package com.unsplash.sdk.api.v1;

import com.unsplash.sdk.api.SupportedApiVersions;
import com.unsplash.sdk.api.UnSplashApiFactory;
import com.unsplash.sdk.api.stubs.UserCredentialsStub;
import com.unsplash.sdk.entities.TokenCredentials;
import com.unsplash.sdk.errors.UnSplashApiError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

class TheUserCanBeAuthorizedWithClient {

    @Mock
    private HttpClient httpClient;
    @Mock
    private HttpResponse responseFromServer;

    @BeforeEach
    void setUp() {
        this.httpClient = mock(HttpClient.class);
        this.responseFromServer = mock(HttpResponse.class);
    }

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

    @Test
    final void the_access_token_and_refresh_token_are_provided_when_the_user_sends_the_correct_information() throws IOException, InterruptedException {
        String jsonResponse = this.loadServerMockResponse("with_access_token.json");
        String authorizationCode = "123433434";
        Mockito.when(responseFromServer.body()).thenReturn(jsonResponse);
        Mockito.when(responseFromServer.statusCode()).thenReturn(200);
        Mockito.when(httpClient.send(any(), any())).thenReturn(responseFromServer);

        UserV1Credentials userV1Credentials = UserCredentialsStub.makeForV1();
        UnSplashApiV1Client client = new UnSplashApiV1Client(httpClient, userV1Credentials);
        TokenCredentials tokenCredentials = client.generateAccessToken(authorizationCode);

        assertEquals("AT-1234", tokenCredentials.getAccessToken());
        assertEquals("RT-1234", tokenCredentials.getRefreshToken());
    }

    @Test
    final void the_system_informs_the_user_about_the_errors_from_the_response() throws IOException, InterruptedException {
        String jsonResponse = this.loadServerMockResponse("invalid_grant.json");
        String authorizationCode = "123433434";
        Mockito.when(responseFromServer.body()).thenReturn(jsonResponse);
        Mockito.when(responseFromServer.statusCode()).thenReturn(401);
        Mockito.when(httpClient.send(any(), any())).thenReturn(responseFromServer);

        UserV1Credentials userV1Credentials = UserCredentialsStub.makeForV1();
        UnSplashApiV1Client client = new UnSplashApiV1Client(httpClient, userV1Credentials);

        UnSplashApiError error = assertThrows(UnSplashApiError.class, () -> {
            client.generateAccessToken(authorizationCode);
        });

        assertTrue(error.getMessage().contains("The provided authorization grant is invalid, expired, revoked, does not match the redirection URI used in the authorization request, or was issued to another client."));
    }

    private String loadServerMockResponse(String relativePath) throws IOException {
        URL url = getClass().getResource("/api_mock_responses/" + relativePath);
        return this.readFile(url.getFile());
    }

    private String readFile(String fileName) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }
            return sb.toString();
        }
    }

}
