package com.develab.carpark.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarParkInformation {
    private BigDecimal id;
    private String carParkNumber;
    private String address;
    private double xCoordinate;
    private double yCoordinate;
    private double latitude;
    private double longitude;
    private String carParkType;
    private String parkingSystemType;
    private String shortTermParking;
    private boolean isFreeParking;
    private boolean isNightParking;
    private int carParkDecks;
    private double gantryHeight;
    private boolean isCarParkBasement;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
