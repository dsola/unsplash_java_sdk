package com.unsplash.sdk.api.v1.resources.profile;

class UserProfileMetrics {
    private Integer totalLikes;
    private Integer totalPhotos;
    private Integer totalCollections;

    public UserProfileMetrics(Integer totalLikes, Integer totalPhotos, Integer totalCollections) {
        this.totalLikes = totalLikes;
        this.totalPhotos = totalPhotos;
        this.totalCollections = totalCollections;
    }

    public Integer getTotalLikes() {
        return totalLikes;
    }

    public Integer getTotalPhotos() {
        return totalPhotos;
    }

    public Integer getTotalCollections() {
        return totalCollections;
    }
}
