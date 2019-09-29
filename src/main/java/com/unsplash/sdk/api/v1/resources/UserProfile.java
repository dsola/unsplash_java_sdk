package com.unsplash.sdk.api.v1.resources;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@JsonDeserialize(using = UserProfileDeserializer.class)
public class UserProfile {
    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private String bio;
    private String location;
    private LocalDateTime updatedTime;
    private UserProfileMetrics metrics;

    public UserProfile(String id, String username, String firstName, String lastName, LocalDateTime updatedTime) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.updatedTime = updatedTime;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    public Optional<String> getBio() {
        return (Objects.isNull(bio)) ? Optional.empty() : Optional.of(bio);
    }

    public Optional<String> getLocation() {
        return (Objects.isNull(location)) ? Optional.empty() : Optional.of(location);
    }

    public Optional<Integer> getTotalCollections() {
        return (Objects.isNull(metrics)) ? Optional.empty() : Optional.of(metrics.getTotalCollections());
    }

    public Optional<Integer> getTotalLikes() {
        return (Objects.isNull(metrics)) ? Optional.empty() : Optional.of(metrics.getTotalLikes());
    }

    public Optional<Integer> getTotalPhotos() {
        return (Objects.isNull(metrics)) ? Optional.empty() : Optional.of(metrics.getTotalPhotos());
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setMetrics(UserProfileMetrics metrics) {
        this.metrics = metrics;
    }
}
