package com.develab.carpark.adapter.output.persistance.postgre.carparkavailability.converter;

import com.develab.carpark.adapter.output.persistance.postgre.carparkavailability.data.CarParkAvailabilityPostgre;
import com.develab.carpark.domain.entity.CarParkAvailability;
import org.springframework.stereotype.Component;

@Component
public class CarParkAvailabilityPostgreConverter {

    public CarParkAvailabilityPostgre convertToDto(CarParkAvailability carParkAvailability){
        CarParkAvailabilityPostgre carParkAvailabilityPostgre = new CarParkAvailabilityPostgre();

        carParkAvailabilityPostgre.setId(carParkAvailability.getId());
        carParkAvailabilityPostgre.setCarParkNumber(carParkAvailability.getCarParkNumber());
        carParkAvailabilityPostgre.setLotType(carParkAvailability.getLotType());
        carParkAvailabilityPostgre.setTotalLots(carParkAvailability.getTotalLots());
        carParkAvailabilityPostgre.setAvailableLots(carParkAvailability.getAvailableLots());
        carParkAvailabilityPostgre.setCreatedAt(carParkAvailability.getCreatedAt());
        carParkAvailabilityPostgre.setUpdatedAt(carParkAvailability.getUpdatedAt());

        return carParkAvailabilityPostgre;
    }

    public CarParkAvailability convertToEntity(CarParkAvailabilityPostgre carParkAvailabilityPostgre){
        CarParkAvailability carParkAvailability = new CarParkAvailability();

        carParkAvailability.setId(carParkAvailabilityPostgre.getId());
        carParkAvailability.setCarParkNumber(carParkAvailabilityPostgre.getCarParkNumber());
        carParkAvailability.setLotType(carParkAvailabilityPostgre.getLotType());
        carParkAvailability.setTotalLots(carParkAvailabilityPostgre.getTotalLots());
        carParkAvailability.setAvailableLots(carParkAvailabilityPostgre.getAvailableLots());
        carParkAvailability.setCreatedAt(carParkAvailabilityPostgre.getCreatedAt());
        carParkAvailability.setUpdatedAt(carParkAvailabilityPostgre.getUpdatedAt());

        return carParkAvailability;
    }
}
