package com.unsplash.sdk.api.v1;

import com.unsplash.sdk.api.stubs.UserCredentialsStub;
import com.unsplash.sdk.api.v1.resources.UserProfile;
import com.unsplash.sdk.entities.TokenCredentials;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

final class TheUserCanSeeInformationFromHisProfile extends ApiClientTest {
    @Test
    final void the_basic_profile_information_is_populated_in_the_aggregate() throws IOException, InterruptedException {
        String jsonResponse = loadServerMockResponse("with_valid_user_profile.json");
        String accessToken = "AT-1234";
        Mockito.when(responseFromServer.body()).thenReturn(jsonResponse);
        Mockito.when(responseFromServer.statusCode()).thenReturn(200);
        Mockito.when(httpClient.send(any(), any())).thenReturn(responseFromServer);

        UserV1Credentials userV1Credentials = UserCredentialsStub.makeForV1();
        UnSplashApiV1Client client = new UnSplashApiV1Client(httpClient, userV1Credentials);
        UserProfile userProfile = client.getUserProfile(accessToken);

        assertEquals("g8Gvi_mO9r8", userProfile.getId());
        assertEquals("solaing", userProfile.getUsername());
        assertEquals("David", userProfile.getFirstName());
        assertEquals("Sola", userProfile.getLastName());
    }
}
