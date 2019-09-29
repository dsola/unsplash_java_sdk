package com.unsplash.sdk.api.v1.resources.profile;

import java.net.MalformedURLException;
import java.net.URL;

public class UserProfilePicture {
    private URL smallPictureUrl;
    private URL mediumPictureUrl;
    private URL largePictureUrl;

    public UserProfilePicture(String smallPictureUrl, String mediumPictureUrl, String largePictureUrl) throws MalformedURLException {
        this.smallPictureUrl = new URL(smallPictureUrl);
        this.mediumPictureUrl = new URL(mediumPictureUrl);
        this.largePictureUrl = new URL(largePictureUrl);
    }

    URL getSmallPictureUrl() {
        return smallPictureUrl;
    }

    URL getMediumPictureUrl() {
        return mediumPictureUrl;
    }

    URL getLargePictureUrl() {
        return largePictureUrl;
    }
}
