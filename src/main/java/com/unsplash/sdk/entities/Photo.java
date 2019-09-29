package com.unsplash.sdk.entities;

import java.awt.*;
import java.net.URL;
import java.time.LocalDateTime;

public interface Photo {
    String getId();

    LocalDateTime getPublishedDate();

    LocalDateTime getUpdatedDate();

    Integer getWidth();

    Integer getHeight();

    Color getColor();

    String getDescription();

    Integer getTotalLikes();

    UserProfile getUser();

    URL getRawUrl();

    URL getFullUrl();

    URL getRegularUrl();

    URL getSmallUrl();

    URL getThumbUrl();
}
