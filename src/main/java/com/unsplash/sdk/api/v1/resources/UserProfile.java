package com.unsplash.sdk.api.v1.resources;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.time.LocalDateTime;

@JsonDeserialize(using = UserProfileDeserializer.class)
public class UserProfile {
    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private String bio;
    private String location;
    private LocalDateTime dateTime;

    public UserProfile(String id, String username, String firstName, String lastName, LocalDateTime dateTime) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateTime = dateTime;
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

    public String getBio() {
        return bio;
    }

    public String getLocation() {
        return location;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
