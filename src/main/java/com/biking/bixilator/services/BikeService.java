package com.biking.bixilator.services;

import com.biking.bixilator.entities.StationInfo;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BikeService {
    public List<StationInfo> filterByLongitude(List<StationInfo> stationInfos, BigDecimal longitude) {
        return stationInfos.stream()
                .filter(stationInfo -> stationInfo.getLongitude().compareTo(longitude) == 0)
                .collect(Collectors.toList());
    }
}
