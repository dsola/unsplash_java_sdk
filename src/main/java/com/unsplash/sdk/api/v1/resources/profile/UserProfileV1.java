package com.unsplash.sdk.api.v1.resources.profile;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.ToString;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@JsonDeserialize(using = UserProfileDeserializer.class)
@ToString
final public class UserProfileV1 implements com.unsplash.sdk.entities.UserProfile {
    private final String id;
    private final String username;
    private final String firstName;
    private final String lastName;
    private String bio;
    private String location;
    private final LocalDateTime updatedTime;
    private UserProfileMetrics metrics;
    private UserProfilePicture profilePicture;

    public UserProfileV1(String id, String username, String firstName, String lastName, LocalDateTime updatedTime) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.updatedTime = updatedTime;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    @Override
    public Optional<String> getBio() {
        return (Objects.isNull(bio)) ? Optional.empty() : Optional.of(bio);
    }

    @Override
    public Optional<String> getLocation() {
        return (Objects.isNull(location)) ? Optional.empty() : Optional.of(location);
    }

    @Override
    public Optional<Integer> getTotalCollections() {
        return (Objects.isNull(metrics)) ? Optional.empty() : Optional.of(metrics.getTotalCollections());
    }

    @Override
    public Optional<Integer> getTotalLikes() {
        return (Objects.isNull(metrics)) ? Optional.empty() : Optional.of(metrics.getTotalLikes());
    }

    @Override
    public Optional<Integer> getTotalPhotos() {
        return (Objects.isNull(metrics)) ? Optional.empty() : Optional.of(metrics.getTotalPhotos());
    }

    @Override
    public Optional<URL> getSmallProfilePicture() {
        return (Objects.isNull(profilePicture)) ? Optional.empty() : Optional.of(profilePicture.getSmallPictureUrl());
    }

    @Override
    public Optional<URL> getMediumProfilePicture() {
        return (Objects.isNull(profilePicture)) ? Optional.empty() : Optional.of(profilePicture.getMediumPictureUrl());
    }

    @Override
    public Optional<URL> getLargeProfilePicture() {
        return (Objects.isNull(profilePicture)) ? Optional.empty() : Optional.of(profilePicture.getLargePictureUrl());
    }

    void setBio(String bio) {
        this.bio = bio;
    }

    void setLocation(String location) {
        this.location = location;
    }

    void setMetrics(UserProfileMetrics metrics) {
        this.metrics = metrics;
    }

    void setProfilePicture(UserProfilePicture profilePicture) {
        this.profilePicture = profilePicture;
    }
}
