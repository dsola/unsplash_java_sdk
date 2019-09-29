package com.unsplash.sdk;

import com.unsplash.sdk.entities.*;
import com.unsplash.sdk.exceptions.WrongJsonUserCredentials;

import java.util.List;

public interface UnSplashClient {

    String generateAuthorizeUrl(List<String> scopes);

    TokenCredentials generateAccessToken(String authorizationCode) throws WrongJsonUserCredentials;

    UserProfile getUserProfile();

    List<Collection> getCollections();

    List<Photo> getPhotos();
}
