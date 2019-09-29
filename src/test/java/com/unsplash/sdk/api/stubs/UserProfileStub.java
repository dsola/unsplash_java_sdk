package com.unsplash.sdk.api.stubs;

import com.github.javafaker.Faker;
import com.unsplash.sdk.entities.UserProfile;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.Optional;

public class UserProfileStub implements UserProfile {

    private String id;
    private String userName;

    public UserProfileStub() {
        Faker faker = new Faker();
        id = faker.code().asin();
        userName = faker.name().username();
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public String getFirstName() {
        return null;
    }

    @Override
    public String getLastName() {
        return null;
    }

    @Override
    public LocalDateTime getUpdatedTime() {
        return null;
    }

    @Override
    public Optional<String> getBio() {
        return Optional.empty();
    }

    @Override
    public Optional<String> getLocation() {
        return Optional.empty();
    }

    @Override
    public Optional<Integer> getTotalCollections() {
        return Optional.empty();
    }

    @Override
    public Optional<Integer> getTotalLikes() {
        return Optional.empty();
    }

    @Override
    public Optional<Integer> getTotalPhotos() {
        return Optional.empty();
    }

    @Override
    public Optional<URL> getSmallProfilePicture() {
        return Optional.empty();
    }

    @Override
    public Optional<URL> getMediumProfilePicture() {
        return Optional.empty();
    }

    @Override
    public Optional<URL> getLargeProfilePicture() {
        return Optional.empty();
    }
}
