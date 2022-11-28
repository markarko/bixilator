package com.biking.bixilator.entities;

import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class StationInfo {
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String name;
    private int capacity;
    private boolean isChargingStation;
    private boolean hasKiosk;
}
