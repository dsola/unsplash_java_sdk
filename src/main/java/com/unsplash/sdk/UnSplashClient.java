package com.unsplash.sdk;

import com.unsplash.sdk.entities.*;

import java.util.List;

public interface UnSplashClient {

    String generateAuthorizeUrl(List<String> scopes);

    TokenCredentials generateAccessToken(String authorizationCode);

    UserProfile getUserProfile();

    List<Collection> getCollections();

    List<Photo> getPhotos();
}
