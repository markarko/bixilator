package com.biking.bixilator.requests;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class DataFetch {
    private static HttpClient httpClient = HttpClient.newBuilder().build();

    /**
     * Fetches information about all bixi stations from the API
     */
    public static void fetchBikeData() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://gbfs.velobixi.com/gbfs/en/station_information.json"))
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

