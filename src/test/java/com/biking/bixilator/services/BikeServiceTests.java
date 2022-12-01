package com.biking.bixilator.services;

import com.biking.bixilator.entities.StationInfo;
import com.biking.bixilator.requests.DataFetch;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
public class BikeServiceTests {
    @Autowired
    private BikeService bikeService;

    @Test
    public void filterByLongitudeTest(){
        List<StationInfo> stationInfos = DataFetch.getAllStationInfos();
        BigDecimal longitude = new BigDecimal(	-73.56295);
        List<StationInfo> filteredStationInfos = bikeService.filterByLongitude(stationInfos, longitude);
        Assertions.assertEquals(filteredStationInfos.size(), 1);
        longitude = new BigDecimal(	-73.525632452465324523546295);
        filteredStationInfos = bikeService.filterByLongitude(stationInfos, longitude);
        Assertions.assertEquals(filteredStationInfos.size(), 0);
        longitude = new BigDecimal(	-73.51035632193089);
        filteredStationInfos = bikeService.filterByLongitude(stationInfos, longitude);
        Assertions.assertEquals(filteredStationInfos.size(), 1);
    }
}
