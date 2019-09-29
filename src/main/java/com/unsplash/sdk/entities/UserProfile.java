package com.unsplash.sdk.entities;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.Optional;

public interface UserProfile {
    String getId();

    String getUsername();

    String getFirstName();

    String getLastName();

    LocalDateTime getUpdatedTime();

    Optional<String> getBio();

    Optional<String> getLocation();

    Optional<Integer> getTotalCollections();

    Optional<Integer> getTotalLikes();

    Optional<Integer> getTotalPhotos();

    Optional<URL> getSmallProfilePicture();

    Optional<URL> getMediumProfilePicture();

    Optional<URL> getLargeProfilePicture();
}
