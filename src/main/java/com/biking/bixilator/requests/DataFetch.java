package com.biking.bixilator.requests;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class DataFetch {
    private static HttpClient httpClient = HttpClient.newBuilder().build();

    /**
     * Fetches information about all bixi stations from the API
     */
    public static void fetchBikeData(String url) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            parseStationInfo(response.body());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Parses the response body and creates entity objects from the data
     * @param responseBody
     */
    public static void parseStationInfo(String responseBody){
        JSONObject stationInfoData = new JSONObject(responseBody);
        JSONObject stationInfoObj = (JSONObject) stationInfoData.get("data");
        JSONArray stationInfos = (JSONArray) stationInfoObj.get("stations");

        for (int i = 0; i < stationInfos.length(); i++){
            JSONObject stationInfo = stationInfos.getJSONObject(i);
            BigDecimal latitude = stationInfo.getBigDecimal("lat");
            BigDecimal longitude = stationInfo.getBigDecimal("lon");
            if (latitude.doubleValue() == 0 || longitude.doubleValue() == 0) continue;
            String name = stationInfo.getString("name");
            int capacity = (int) stationInfo.getNumber("capacity");
            boolean isChargingStation = stationInfo.getBoolean("is_charging");
            boolean hasKiosk = stationInfo.getBoolean("has_kiosk");
        }
    }
}

