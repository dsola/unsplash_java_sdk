package com.unsplash.sdk.api.v1;

import com.unsplash.sdk.api.stubs.UserCredentialsStub;
import com.unsplash.sdk.api.v1.resources.UserProfile;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;

final class TheUserCanSeeInformationFromHisProfile extends ApiClientTest {
    @Test
    final void the_basic_profile_information_is_provided() throws IOException, InterruptedException {
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
        assertEquals("2019-09-28T06:21:33", userProfile.getUpdatedTime().format(DateTimeFormatter.ISO_DATE_TIME));
        assertFalse(userProfile.getBio().isPresent());
        assertFalse(userProfile.getLocation().isPresent());
    }

    @Test
    final void the_user_metrics_are_provided() throws IOException, InterruptedException {
        String jsonResponse = loadServerMockResponse("with_valid_user_profile.json");
        String accessToken = "AT-1234";
        Mockito.when(responseFromServer.body()).thenReturn(jsonResponse);
        Mockito.when(responseFromServer.statusCode()).thenReturn(200);
        Mockito.when(httpClient.send(any(), any())).thenReturn(responseFromServer);

        UserV1Credentials userV1Credentials = UserCredentialsStub.makeForV1();
        UnSplashApiV1Client client = new UnSplashApiV1Client(httpClient, userV1Credentials);
        UserProfile userProfile = client.getUserProfile(accessToken);

        assertEquals(1, userProfile.getTotalCollections().get());
        assertEquals(0, userProfile.getTotalLikes().get());
        assertEquals(3, userProfile.getTotalPhotos().get());
    }

    @Test
    final void the_bio_and_the_location_are_provided() throws IOException, InterruptedException {
        String jsonResponse = loadServerMockResponse("with_user_profile_bio_and_location.json");
        String accessToken = "AT-1234";
        Mockito.when(responseFromServer.body()).thenReturn(jsonResponse);
        Mockito.when(responseFromServer.statusCode()).thenReturn(200);
        Mockito.when(httpClient.send(any(), any())).thenReturn(responseFromServer);

        UserV1Credentials userV1Credentials = UserCredentialsStub.makeForV1();
        UnSplashApiV1Client client = new UnSplashApiV1Client(httpClient, userV1Credentials);
        UserProfile userProfile = client.getUserProfile(accessToken);

        assertEquals("An amazing Software Engineer :)", userProfile.getBio().get());
        assertEquals("Van Nijenrodeweg 214, Amsterdam", userProfile.getLocation().get());
    }
}
