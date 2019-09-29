package com.unsplash.sdk.api.stubs;

import com.github.javafaker.Faker;
import com.unsplash.sdk.entities.Collection;
import com.unsplash.sdk.entities.UserProfile;

import java.time.LocalDateTime;

public class CollectionStub implements Collection {

    private Integer id;

    CollectionStub() {
        Faker faker = new Faker();
        id = faker.number().randomDigit();
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
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
    public Boolean isCurated() {
        return null;
    }

    @Override
    public Boolean isFeatured() {
        return null;
    }

    @Override
    public Boolean isPrivate() {
        return null;
    }

    @Override
    public Integer getTotalPhotos() {
        return null;
    }

    @Override
    public UserProfile getUser() {
        return null;
    }
}
