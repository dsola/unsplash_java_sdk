package com.unsplash.sdk.api.v1;

import com.unsplash.sdk.api.stubs.UserCredentialsStub;
import com.unsplash.sdk.entities.Photo;
import com.unsplash.sdk.entities.UserProfile;
import com.unsplash.sdk.errors.UnSplashApiError;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class TheUserIsAbleToRequestPhotos extends ApiClientTest {
    @Test
    final void for_an_empty_list_of_photos() throws IOException, InterruptedException {
        String jsonResponse = loadServerMockResponse("empty_collection.json");
        String accessToken = "AT-1234";
        Mockito.when(responseFromServer.body()).thenReturn(jsonResponse);
        Mockito.when(responseFromServer.statusCode()).thenReturn(200);
        Mockito.when(httpClient.send(any(), any())).thenReturn(responseFromServer);

        UserV1Credentials userV1Credentials = UserCredentialsStub.makeForV1();
        UnSplashApiV1Client client = new UnSplashApiV1Client(httpClient, userV1Credentials);
        List<Photo> photos = client.getPhotos(accessToken);

        assertTrue(photos.isEmpty());
    }

    @Test
    final void with_one_photo() throws IOException, InterruptedException {
        String jsonResponse = loadServerMockResponse("with_one_photo.json");
        String accessToken = "AT-1234";
        Mockito.when(responseFromServer.body()).thenReturn(jsonResponse);
        Mockito.when(responseFromServer.statusCode()).thenReturn(200);
        Mockito.when(httpClient.send(any(), any())).thenReturn(responseFromServer);

        UserV1Credentials userV1Credentials = UserCredentialsStub.makeForV1();
        UnSplashApiV1Client client = new UnSplashApiV1Client(httpClient, userV1Credentials);
        List<Photo> photos = client.getPhotos(accessToken);

        Photo photo = photos.get(0);

        assertEquals("4uY9123rRzI", photo.getId());
        assertEquals(6442, photo.getWidth());
        assertEquals(4295, photo.getHeight());
        assertEquals("It's not about how much space you have, but how you use the space...", photo.getDescription());
        assertEquals(
            new URL("https://images.unsplash.com/photo-1562184647-5c4f531aef0d?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjkzNjI5fQ"),
            photo.getRawUrl()
        );
        assertEquals(
            new URL("https://images.unsplash.com/photo-1562184647-5c4f531aef0d?ixlib=rb-1.2.1&q=85&fm=jpg&crop=entropy&cs=srgb&ixid=eyJhcHBfaWQiOjkzNjI5fQ"),
            photo.getFullUrl()
        );
        assertEquals(
                new URL("https://images.unsplash.com/photo-1562184647-5c4f531aef0d?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max&ixid=eyJhcHBfaWQiOjkzNjI5fQ"),
                photo.getRegularUrl()
        );
        assertEquals(
                new URL("https://images.unsplash.com/photo-1562184647-5c4f531aef0d?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max&ixid=eyJhcHBfaWQiOjkzNjI5fQ"),
                photo.getSmallUrl()
        );
        assertEquals(
                new URL("https://images.unsplash.com/photo-1562184647-5c4f531aef0d?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max&ixid=eyJhcHBfaWQiOjkzNjI5fQ"),
                photo.getThumbUrl()
        );
        assertEquals(120, photo.getTotalLikes());

        assertEquals("2019-07-03T16:11:16", photo.getPublishedDate().format(DateTimeFormatter.ISO_DATE_TIME));
        assertEquals("2019-09-27T01:08:13", photo.getUpdatedDate().format(DateTimeFormatter.ISO_DATE_TIME));
    }

    @Test
    final void the_photo_includes_information_about_the_user() throws IOException, InterruptedException {
        String jsonResponse = loadServerMockResponse("with_one_photo.json");
        String accessToken = "AT-1234";
        Mockito.when(responseFromServer.body()).thenReturn(jsonResponse);
        Mockito.when(responseFromServer.statusCode()).thenReturn(200);
        Mockito.when(httpClient.send(any(), any())).thenReturn(responseFromServer);

        UserV1Credentials userV1Credentials = UserCredentialsStub.makeForV1();
        UnSplashApiV1Client client = new UnSplashApiV1Client(httpClient, userV1Credentials);
        List<Photo> photos = client.getPhotos(accessToken);

        Photo photo = photos.get(0);

        UserProfile userProfile = photo.getUser();
        assertEquals("room", userProfile.getUsername());
    }

    @Test
    final void when_there_are_a_lot_of_collections() throws IOException, InterruptedException {
        String jsonResponse = loadServerMockResponse("with_some_photos.json");
        String accessToken = "AT-1234";
        Mockito.when(responseFromServer.body()).thenReturn(jsonResponse);
        Mockito.when(responseFromServer.statusCode()).thenReturn(200);
        Mockito.when(httpClient.send(any(), any())).thenReturn(responseFromServer);

        UserV1Credentials userV1Credentials = UserCredentialsStub.makeForV1();
        UnSplashApiV1Client client = new UnSplashApiV1Client(httpClient, userV1Credentials);
        List<Photo> photos = client.getPhotos(accessToken);

        assertEquals(10, photos.size());
    }

    @Test
    final void the_system_notifies_when_the_user_is_not_authorized() throws IOException, InterruptedException {
        String jsonResponse = loadServerMockResponse("invalid_access_token.json");
        String accessToken = "AT-1234";
        Mockito.when(responseFromServer.body()).thenReturn(jsonResponse);
        Mockito.when(responseFromServer.statusCode()).thenReturn(401);
        Mockito.when(httpClient.send(any(), any())).thenReturn(responseFromServer);

        UserV1Credentials userV1Credentials = UserCredentialsStub.makeForV1();
        UnSplashApiV1Client client = new UnSplashApiV1Client(httpClient, userV1Credentials);
        assertThrows(UnSplashApiError.class,  () -> {
            client.getPhotos(accessToken);
        });
    }
}
