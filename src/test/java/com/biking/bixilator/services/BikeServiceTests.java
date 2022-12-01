package com.biking.bixilator.services;

import com.biking.bixilator.entities.StationInfo;
import com.biking.bixilator.requests.DataFetch;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class BikeServiceTests {
    @Autowired
    private BikeService bikeService;
    private static List<StationInfo> stationInfos;

    @BeforeAll
    public static void init(){
        stationInfos = DataFetch.getAllStationInfos();
    }
    @Test
    public void filterByLongitudeTest(){
        BigDecimal longitude = new BigDecimal(	-73.56295);
        List<StationInfo> filteredStationInfos = bikeService.filterByLongitude(stationInfos, longitude);
        Assertions.assertEquals(1, filteredStationInfos.size());
        longitude = new BigDecimal(	-73.525632452465324523546295);
        filteredStationInfos = bikeService.filterByLongitude(stationInfos, longitude);
        Assertions.assertEquals(0, filteredStationInfos.size());
        longitude = new BigDecimal(	-73.51035632193089);
        filteredStationInfos = bikeService.filterByLongitude(stationInfos, longitude);
        Assertions.assertEquals(1, filteredStationInfos.size());
    }
    @Test
    public void filterByNameTest(){
        String name = "asdflaskhasdf";
        List<StationInfo> filteredStationInfos = bikeService.filterByName(stationInfos, name);
        Assertions.assertEquals(0, filteredStationInfos.size());
        name = "Crescent / de maisonneuve";
        filteredStationInfos = bikeService.filterByName(stationInfos, name);
        Assertions.assertEquals(1, filteredStationInfos.size());
    }
}
