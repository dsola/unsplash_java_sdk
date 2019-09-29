package com.unsplash.sdk.api.v1;

import com.unsplash.sdk.api.stubs.UserCredentialsStub;
import com.unsplash.sdk.entities.Collection;
import com.unsplash.sdk.entities.UserProfile;
import com.unsplash.sdk.errors.UnSplashApiError;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

final class TheUserIsAbleToRequestCollections extends ApiClientTest {
    @Test
    final void for_an_empty_list_of_collections() throws IOException, InterruptedException {
        String jsonResponse = loadServerMockResponse("empty_collection.json");
        String accessToken = "AT-1234";
        Mockito.when(responseFromServer.body()).thenReturn(jsonResponse);
        Mockito.when(responseFromServer.statusCode()).thenReturn(200);
        Mockito.when(httpClient.send(any(), any())).thenReturn(responseFromServer);

        UserV1Credentials userV1Credentials = UserCredentialsStub.makeForV1();
        UnSplashApiV1Client client = new UnSplashApiV1Client(httpClient, userV1Credentials);
        List<Collection> collections = client.getCollections(accessToken);

        assertTrue(collections.isEmpty());
    }

    @Test
    final void with_one_collection() throws IOException, InterruptedException {
        String jsonResponse = loadServerMockResponse("with_one_collection.json");
        String accessToken = "AT-1234";
        Mockito.when(responseFromServer.body()).thenReturn(jsonResponse);
        Mockito.when(responseFromServer.statusCode()).thenReturn(200);
        Mockito.when(httpClient.send(any(), any())).thenReturn(responseFromServer);

        UserV1Credentials userV1Credentials = UserCredentialsStub.makeForV1();
        UnSplashApiV1Client client = new UnSplashApiV1Client(httpClient, userV1Credentials);
        List<Collection> collections = client.getCollections(accessToken);

        Collection collection = collections.get(0);

        assertEquals(1248080, collection.getId());
        assertEquals("October Afternoon", collection.getTitle());
        assertEquals(
        "Autumn leaves and pumpkins, wild mushrooms in the woods, the colors of the fall. When I think of October, this is what I see.",
            collection.getDescription()
        );
        assertEquals(false, collection.isCurated());
        assertEquals(true, collection.isFeatured());
        assertEquals(false, collection.isPrivate());
        assertEquals(119, collection.getTotalPhotos());
        assertEquals("2019-09-27T11:16:01", collection.getPublishedDate().format(DateTimeFormatter.ISO_DATE_TIME));
        assertEquals("2019-09-27T11:16:01", collection.getUpdatedDate().format(DateTimeFormatter.ISO_DATE_TIME));
    }

    @Test
    final void the_collection_includes_information_about_the_user() throws IOException, InterruptedException {
        String jsonResponse = loadServerMockResponse("with_one_collection.json");
        String accessToken = "AT-1234";
        Mockito.when(responseFromServer.body()).thenReturn(jsonResponse);
        Mockito.when(responseFromServer.statusCode()).thenReturn(200);
        Mockito.when(httpClient.send(any(), any())).thenReturn(responseFromServer);

        UserV1Credentials userV1Credentials = UserCredentialsStub.makeForV1();
        UnSplashApiV1Client client = new UnSplashApiV1Client(httpClient, userV1Credentials);
        List<Collection> collections = client.getCollections(accessToken);

        Collection collection = collections.get(0);

        UserProfile userProfile = collection.getUser();
        assertEquals("viazavier", userProfile.getUsername());
    }

    @Test
    final void when_there_are_a_lot_of_collections() throws IOException, InterruptedException {
        String jsonResponse = loadServerMockResponse("with_a_lot_of_collections.json");
        String accessToken = "AT-1234";
        Mockito.when(responseFromServer.body()).thenReturn(jsonResponse);
        Mockito.when(responseFromServer.statusCode()).thenReturn(200);
        Mockito.when(httpClient.send(any(), any())).thenReturn(responseFromServer);

        UserV1Credentials userV1Credentials = UserCredentialsStub.makeForV1();
        UnSplashApiV1Client client = new UnSplashApiV1Client(httpClient, userV1Credentials);
        List<Collection> collections = client.getCollections(accessToken);

        assertEquals(10, collections.size());
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
        assertThrows(UnSplashApiError.class,  () -> client.getCollections(accessToken));
    }
}
