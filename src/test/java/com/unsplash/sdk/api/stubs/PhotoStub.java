package com.unsplash.sdk.api.stubs;

import com.github.javafaker.Faker;
import com.unsplash.sdk.entities.Photo;
import com.unsplash.sdk.entities.UserProfile;

import java.awt.*;
import java.net.URL;
import java.time.LocalDateTime;

public class PhotoStub implements Photo {

    private String id;

    PhotoStub() {
        Faker faker = new Faker();
        id = faker.code().asin();
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public LocalDateTime getPublishedDate() {
        return null;
    }

    @Override
    public LocalDateTime getUpdatedDate() {
        return null;
    }

    @Override
    public Integer getWidth() {
        return null;
    }

    @Override
    public Integer getHeight() {
        return null;
    }

    @Override
    public Color getColor() {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public Integer getTotalLikes() {
        return null;
    }

    @Override
    public UserProfile getUser() {
        return null;
    }

    @Override
    public URL getRawUrl() {
        return null;
    }

    @Override
    public URL getFullUrl() {
        return null;
    }

    @Override
    public URL getRegularUrl() {
        return null;
    }

    @Override
    public URL getSmallUrl() {
        return null;
    }

    @Override
    public URL getThumbUrl() {
        return null;
    }
}
