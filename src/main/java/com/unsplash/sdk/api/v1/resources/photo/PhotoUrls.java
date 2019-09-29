package com.unsplash.sdk.api.v1.resources.photo;

import java.net.URL;

public class PhotoUrls {
    private final URL regular;
    private final URL full;
    private final URL small;
    private final URL thumb;
    private final URL raw;

    public PhotoUrls(URL regular, URL full, URL small, URL thumb, URL raw) {
        this.regular = regular;
        this.full = full;
        this.small = small;
        this.thumb = thumb;
        this.raw = raw;
    }

    public URL getRegular() {
        return regular;
    }

    public URL getFull() {
        return full;
    }

    public URL getSmall() {
        return small;
    }

    public URL getThumb() {
        return thumb;
    }

    public URL getRaw() {
        return raw;
    }
}
