package com.develab.carpark.adapter.output.persistance.postgre.carparkinformation.data;

import com.develab.carpark.util.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Table("public.\"car_park_information\"")
@NoArgsConstructor
@AllArgsConstructor
public class CarParkInformationPostgre extends AbstractEntity<CarParkInformationPostgre, BigDecimal> {

    @Id
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
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;


}
