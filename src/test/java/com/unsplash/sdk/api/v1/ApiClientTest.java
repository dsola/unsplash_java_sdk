package com.unsplash.sdk.api.v1;

import org.junit.jupiter.api.BeforeEach;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;

import static org.mockito.Mockito.mock;

abstract class ApiClientTest {
    HttpClient httpClient;
    HttpResponse responseFromServer;

    @BeforeEach
    void setUp() {
        this.httpClient = mock(HttpClient.class);
        this.responseFromServer = mock(HttpResponse.class);
    }

    String loadServerMockResponse(String relativePath) throws IOException {
        URL url = getClass().getResource("/api_mock_responses/" + relativePath);
        return this.readFile(url.getFile());
    }

    private String readFile(String fileName) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }
            return sb.toString();
        }
    }
}
