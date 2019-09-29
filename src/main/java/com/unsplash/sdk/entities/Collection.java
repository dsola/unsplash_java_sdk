package com.unsplash.sdk.entities;

import java.time.LocalDateTime;

public interface Collection {
    Integer getId();

    String getTitle();

    String getDescription();

    LocalDateTime getPublishedDate();

    LocalDateTime getUpdatedDate();

    Boolean isCurated();

    Boolean isFeatured();

    Boolean isPrivate();

    Integer getTotalPhotos();

    UserProfile getUser();
}
