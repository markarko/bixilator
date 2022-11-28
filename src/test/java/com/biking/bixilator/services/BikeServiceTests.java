package com.biking.bixilator.services;

import com.biking.bixilator.entities.StationInfo;
import com.biking.bixilator.requests.DataFetch;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.http.HttpResponse;
import java.util.List;

@SpringBootTest
public class BikeServiceTests {
    @Autowired
    private BikeService bikeService;

    @Test
    public void filterByLongitudeTest(){
        HttpResponse<String> response = DataFetch.fetchBikeData("https://gbfs.velobixi.com/gbfs/en/station_information.json");
        List<StationInfo> stationInfos = DataFetch.parseStationInfo(response.body());
        BigDecimal longitude = new BigDecimal(	-73.56295);
        BigDecimal trunkedLongitude = longitude.setScale(15, RoundingMode.DOWN);
        List<StationInfo> filteredStationInfos = bikeService.filterByLongitude(stationInfos, trunkedLongitude);
        Assertions.assertEquals(filteredStationInfos.size(), 1);
        longitude = new BigDecimal(	-74.525632452465324523546295);
        trunkedLongitude = longitude.setScale(15, RoundingMode.DOWN);
        filteredStationInfos = bikeService.filterByLongitude(stationInfos, trunkedLongitude);
        Assertions.assertEquals(filteredStationInfos.size(), 0);
    }
}
