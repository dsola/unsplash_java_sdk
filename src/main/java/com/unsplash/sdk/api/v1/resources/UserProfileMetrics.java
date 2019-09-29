package com.unsplash.sdk.api.v1.resources;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
class UserProfileMetrics {
    private Integer $totalLikes;
    private Integer $totalPhotos;
    private Integer $totalCollections;
}
