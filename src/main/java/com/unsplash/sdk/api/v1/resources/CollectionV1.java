package com.unsplash.sdk.api.v1.resources;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.unsplash.sdk.entities.UserProfile;

import java.time.LocalDateTime;

@JsonDeserialize(using = CollectionDeserializer.class)
public class CollectionV1 implements com.unsplash.sdk.entities.Collection {
    private Integer id;
    private String title;
    private String description;
    private LocalDateTime publishedDate;
    private LocalDateTime updatedDate;
    private Boolean isCurated;
    private Boolean isFeatured;
    private Boolean isPrivate;
    private Integer totalPhotos;
    private UserProfile user;

    public CollectionV1(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public LocalDateTime getPublishedDate() {
        return publishedDate;
    }

    @Override
    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    @Override
    public Boolean isCurated() {
        return isCurated;
    }

    @Override
    public Boolean isFeatured() {
        return isFeatured;
    }

    @Override
    public Boolean isPrivate() {
        return isPrivate;
    }

    @Override
    public Integer getTotalPhotos() {
        return totalPhotos;
    }

    @Override
    public UserProfile getUser() {
        return user;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPublishedDate(LocalDateTime publishedDate) {
        this.publishedDate = publishedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public void setCurated(Boolean curated) {
        isCurated = curated;
    }

    public void setFeatured(Boolean featured) {
        isFeatured = featured;
    }

    public void setPrivate(Boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public void setTotalPhotos(Integer totalPhotos) {
        this.totalPhotos = totalPhotos;
    }

    public void setUser(UserProfile user) {
        this.user = user;
    }
}
