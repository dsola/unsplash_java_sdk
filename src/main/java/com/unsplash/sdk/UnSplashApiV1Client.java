package com.unsplash.sdk;

import java.net.http.HttpClient;

public class UnSplashApiV1Client {

    private HttpClient $httpClient;

    public UnSplashApiV1Client() {
        this.$httpClient = HttpClient.newHttpClient();
    }

    public int getUserProfile() {
        return 1;
    }
}
