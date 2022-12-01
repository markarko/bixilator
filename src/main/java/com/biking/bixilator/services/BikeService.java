package com.biking.bixilator.services;

import com.biking.bixilator.entities.StationInfo;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BikeService {
    /**
     * Filters the station info array to only get those that has the same longitude as in the input
     * @param stationInfos
     * @param longitude
     * @return 
     */
    public List<StationInfo> filterByLongitude(List<StationInfo> stationInfos, BigDecimal longitude) {
        return stationInfos.stream()
                .filter(stationInfo -> stationInfo.getLongitude().doubleValue() == longitude.doubleValue())
                .collect(Collectors.toList());
    }
}
;