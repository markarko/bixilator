package com.biking.bixilator.services;

import com.biking.bixilator.entities.StationInfo;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BikeService {
    /**
     * Filters the station info array to only get those that have the same longitude as in the input
     * @param stationInfos station info array to filter
     * @param longitude longitude based on which station infos will be filtered
     * @return filtered station info array
     */
    public List<StationInfo> filterByLongitude(List<StationInfo> stationInfos, BigDecimal longitude) {
        return stationInfos.stream()
                .filter(stationInfo -> stationInfo.getLongitude().doubleValue() == longitude.doubleValue())
                .collect(Collectors.toList());
    }

    /**
     * Filters the station info array to only get those whose name contain the name passed as input
     * @param stationInfos station info array to filter
     * @param name name based on which station infos will be filtered
     * @return filtered station info array
     */
    public List<StationInfo> filterByName(List<StationInfo> stationInfos, String name) {
        return stationInfos.stream()
                .filter(stationInfo -> stationInfo.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }
}