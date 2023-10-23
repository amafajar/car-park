package com.develab.carpark.domain.service;

import com.develab.carpark.domain.entity.CarParkInformation;
import com.develab.carpark.domain.valueobject.CarParkInformationStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class CarParkInformationService {

    public CarParkInformation setStatusInfo(CarParkInformation carParkInformation, CarParkInformationStatus status){
        carParkInformation.setStatus(status.toString());
        carParkInformation.setCreatedAt(LocalDateTime.now(ZoneId.of("Asia/Jakarta")));
        carParkInformation.setUpdatedAt(LocalDateTime.now(ZoneId.of("Asia/Jakarta")));

        return carParkInformation;
    }

    public CarParkInformation changeStatus(CarParkInformation carParkInformation, CarParkInformationStatus status){
        if (carParkInformation.getLatitude() != 0 && carParkInformation.getLongitude() != 0){
            carParkInformation.setStatus(status.toString());
            carParkInformation.setUpdatedAt(LocalDateTime.now(ZoneId.of("Asia/Jakarta")));
        }

        return carParkInformation;
    }
}
