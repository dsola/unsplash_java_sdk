package com.unsplash.sdk;

import com.unsplash.sdk.api.SupportedApiVersions;
import com.unsplash.sdk.api.v1.UserV1Credentials;
import com.unsplash.sdk.entities.*;
import com.unsplash.sdk.exceptions.WrongJsonUserCredentials;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

@Disabled
class FollowingTheReadmeInstructions {

    private final String clientId = "02ee28ed35a8ef3dedb190bc4cea00f799011ae2403455f5f03c743d1d62fc3d";
    private final String clientSecret = "8587e2ea911c7380691f5590010e3409e5fb0a976e293d6cf07a18360cbd5cde";
    private final String redirectUri = "urn:ietf:wg:oauth:2.0:oob";
    private final String authorizationCode = "5a264a201536d6c4c67757818df698f2cbf8cdb7a7eb7fb85c1c9064aaee3d97";

    @Test
    final void following_the_readme_the_user_can_generate_the_authorization_url() {
        /*
            1. First, we create the UserCredentials object for the V1 of this API.
             - We need the access key, secret key and a valid redirect URI
             - You can find this information in https://unsplash.com/oauth/applications/YOUR-APPLICATION-ID
         */
        UserCredentials userCredentials = new UserV1Credentials(
            clientId,
            clientSecret,
            redirectUri
        );
        /*
           2. Now we initialize the client using the credentials and specifying the version
             - Remember that the version needs to be supported by the SDK, otherwise an error is thrown
         */
        UnSplashSdkClient client = UnSplashSdkClient.initialize(SupportedApiVersions.VERSION_1, userCredentials);
        /*
            3. We need to obtain the authorization code. In order to do that, we need the authorization URL.
              - This client provides you the URL, but later you need to access to that URL and get the Authorization Code
              - The authorization code will be included as a URI parameter called code
              - You can choose which permission scopes you want to grant for this authorization
              - You can generate the URL without permission scopes as well
              - IMPORTANT - to be able to use all the functions of the client, please add the same codes as the example on bellow
         */
        List<String> scopes = new ArrayList<>();
        scopes.add("read_user");
        scopes.add("read_photos");
        scopes.add("read_collections");
        System.out.println("Authorization URL -> " + client.generateAuthorizeUrl(scopes));
    }

    @Test
    final void following_the_instructions_the_user_can_generate_the_access_token() throws WrongJsonUserCredentials {
        UserCredentials userCredentials = new UserV1Credentials(
                clientId,
                clientSecret,
                redirectUri
        );
        UnSplashSdkClient client = UnSplashSdkClient.initialize(SupportedApiVersions.VERSION_1, userCredentials);
        /*
            4. Once you have the authorization code, we can generate the token credentials with the client
            - The client generates the token credentials which are the access token and the refresh token.
            - Those credentials are saved in the client so you can start making calls straight away
         */
        TokenCredentials credentials = client.generateAccessToken(authorizationCode);
        System.out.println("Access Token -> " + credentials.getAccessToken());
        System.out.println("Refresh Token -> " + credentials.getRefreshToken());
    }

    @Test
    final void following_the_instructions_the_user_can_read_the_profile() throws WrongJsonUserCredentials {
        UserCredentials userCredentials = new UserV1Credentials(
                clientId,
                clientSecret,
                redirectUri
        );
        UnSplashSdkClient client = UnSplashSdkClient.initialize(SupportedApiVersions.VERSION_1, userCredentials);
        client.generateAccessToken(authorizationCode);
        /*
            - We can read from the user's profile once we have the access token generated using the client
            - Remember to add the permission scope read_user when you generate the authorization URL
         */
        UserProfile userProfile = client.getUserProfile();
        System.out.println("User Profile -> " + userProfile.toString());
    }

    @Test
    final void following_the_instructions_the_user_can_read_the_photos() throws WrongJsonUserCredentials {
        UserCredentials userCredentials = new UserV1Credentials(
                clientId,
                clientSecret,
                redirectUri
        );
        UnSplashSdkClient client = UnSplashSdkClient.initialize(SupportedApiVersions.VERSION_1, userCredentials);
        client.generateAccessToken(authorizationCode);
        /*
            - We can obtain the photos from Unsplash once we have the access token generated using the client
            - Remember to add the permission scope read_photos when you generate the authorization URL
         */
        List<Photo> photos = client.getPhotos();
        photos.forEach(p -> System.out.println("Photo -> " + p.toString()));
    }

    @Test
    final void following_the_instructions_the_user_can_read_the_collections() throws WrongJsonUserCredentials {
        UserCredentials userCredentials = new UserV1Credentials(
                clientId,
                clientSecret,
                redirectUri
        );
        UnSplashSdkClient client = UnSplashSdkClient.initialize(SupportedApiVersions.VERSION_1, userCredentials);
        client.generateAccessToken(authorizationCode);
        /*
            - We can obtain the collections from Unsplash once we have the access token generated using the client
            - Remember to add the permission scope read_collections when you generate the authorization URL
         */
        List<Collection> collections = client.getCollections();
        collections.forEach(c -> System.out.println("Collection -> " + c.toString()));
    }
}
