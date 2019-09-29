package com.unsplash.sdk.api.v1.resources.photo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.unsplash.sdk.entities.UserProfile;

import java.awt.*;
import java.net.URL;
import java.time.LocalDateTime;

@JsonDeserialize(using = PhotoDeserializer.class)
public class PhotoV1 implements com.unsplash.sdk.entities.Photo {
    private String id;
    private LocalDateTime publishedDate;
    private LocalDateTime updatedDate;
    private Integer width;
    private Integer height;
    private Color color;
    private String description;
    private Integer totalLikes;
    private PhotoUrls photoUrls;
    private UserProfile user;

    public PhotoV1(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
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
    public Integer getWidth() {
        return width;
    }

    @Override
    public Integer getHeight() {
        return height;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public Integer getTotalLikes() {
        return totalLikes;
    }

    @Override
    public UserProfile getUser() {
        return user;
    }

    @Override
    public URL getRawUrl() {
        return photoUrls.getRaw();
    }

    @Override
    public URL getFullUrl() {
        return photoUrls.getFull();
    }

    @Override
    public URL getRegularUrl() {
        return photoUrls.getRegular();
    }

    @Override
    public URL getSmallUrl() {
        return photoUrls.getSmall();
    }

    @Override
    public URL getThumbUrl() {
        return photoUrls.getThumb();
    }

    public void setPublishedDate(LocalDateTime publishedDate) {
        this.publishedDate = publishedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTotalLikes(Integer totalLikes) {
        this.totalLikes = totalLikes;
    }

    public void setPhotoUrls(PhotoUrls photoUrls) {
        this.photoUrls = photoUrls;
    }

    public void setUser(UserProfile user) {
        this.user = user;
    }
}
