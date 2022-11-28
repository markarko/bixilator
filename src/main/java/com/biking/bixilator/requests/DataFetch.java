package com.biking.bixilator.requests;

import com.biking.bixilator.entities.StationInfo;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

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
     * @return all station info objects created
     */
    public static List<StationInfo> parseStationInfo(String responseBody){
        JSONObject stationInfoRes = new JSONObject(responseBody);
        JSONObject stationInfoData = (JSONObject) stationInfoRes.get("data");
        JSONArray stationInfosArr = (JSONArray) stationInfoData.get("stations");

        List<StationInfo> stationInfos = new ArrayList<>();
        for (int i = 0; i < stationInfosArr.length(); i++){
            JSONObject stationInfoObj = stationInfosArr.getJSONObject(i);
            BigDecimal latitude = stationInfoObj.getBigDecimal("lat");
            BigDecimal longitude = stationInfoObj.getBigDecimal("lon");
            if (latitude.doubleValue() == 0 || longitude.doubleValue() == 0) continue;
            String name = stationInfoObj.getString("name");
            int capacity = (int) stationInfoObj.getNumber("capacity");
            boolean isChargingStation = stationInfoObj.getBoolean("is_charging");
            boolean hasKiosk = stationInfoObj.getBoolean("has_kiosk");

            StationInfo stationInfo = StationInfo.builder()
                    .latitude(latitude)
                    .longitude(longitude)
                    .name(name)
                    .capacity(capacity)
                    .isChargingStation(isChargingStation)
                    .hasKiosk(hasKiosk)
                    .build();
            stationInfos.add(stationInfo);
        }
        return stationInfos;
    }
}

