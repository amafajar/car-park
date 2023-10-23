package com.develab.carpark.adapter.output.persistance.postgre.carparkinformation.converter;

import com.develab.carpark.adapter.output.persistance.postgre.carparkinformation.data.CarParkInformationPostgre;
import com.develab.carpark.domain.entity.CarParkInformation;
import org.springframework.stereotype.Component;

@Component
public class CarParkInformationPostgreConverter {

    public CarParkInformationPostgre convertToDto(CarParkInformation carParkInformation){
        CarParkInformationPostgre carParkInformationPostgre = new CarParkInformationPostgre();

        carParkInformationPostgre.setId(carParkInformation.getId());
        carParkInformationPostgre.setCarParkNumber(carParkInformation.getCarParkNumber());
        carParkInformationPostgre.setAddress(carParkInformation.getAddress());
        carParkInformationPostgre.setXCoordinate(carParkInformation.getXCoordinate());
        carParkInformationPostgre.setYCoordinate(carParkInformation.getYCoordinate());
        carParkInformationPostgre.setLatitude(carParkInformation.getLatitude());
        carParkInformationPostgre.setLongitude(carParkInformation.getLongitude());
        carParkInformationPostgre.setCarParkType(carParkInformation.getCarParkType());
        carParkInformationPostgre.setParkingSystemType(carParkInformation.getParkingSystemType());
        carParkInformationPostgre.setShortTermParking(carParkInformation.getShortTermParking());
        carParkInformationPostgre.setFreeParking(carParkInformation.isFreeParking());
        carParkInformationPostgre.setNightParking(carParkInformation.isNightParking());
        carParkInformationPostgre.setGantryHeight(carParkInformation.getGantryHeight());
        carParkInformationPostgre.setCarParkBasement(carParkInformation.isCarParkBasement());
        carParkInformationPostgre.setStatus(carParkInformation.getStatus());
        carParkInformationPostgre.setCreatedAt(carParkInformation.getCreatedAt());
        carParkInformationPostgre.setUpdatedAt(carParkInformation.getUpdatedAt());

        return carParkInformationPostgre;
    }

    public CarParkInformation convertToEntity(CarParkInformationPostgre carParkInformationPostgre){
        CarParkInformation carParkInformation = new CarParkInformation();

        carParkInformation.setId(carParkInformationPostgre.getId());
        carParkInformation.setCarParkNumber(carParkInformationPostgre.getCarParkNumber());
        carParkInformation.setAddress(carParkInformationPostgre.getAddress());
        carParkInformation.setXCoordinate(carParkInformationPostgre.getXCoordinate());
        carParkInformation.setYCoordinate(carParkInformationPostgre.getYCoordinate());
        carParkInformation.setLatitude(carParkInformationPostgre.getLatitude());
        carParkInformation.setLongitude(carParkInformationPostgre.getLongitude());
        carParkInformation.setCarParkType(carParkInformationPostgre.getCarParkType());
        carParkInformation.setParkingSystemType(carParkInformationPostgre.getParkingSystemType());
        carParkInformation.setShortTermParking(carParkInformationPostgre.getShortTermParking());
        carParkInformation.setFreeParking(carParkInformationPostgre.isFreeParking());
        carParkInformation.setNightParking(carParkInformationPostgre.isNightParking());
        carParkInformation.setGantryHeight(carParkInformationPostgre.getGantryHeight());
        carParkInformation.setCarParkBasement(carParkInformationPostgre.isCarParkBasement());
        carParkInformation.setStatus(carParkInformationPostgre.getStatus());
        carParkInformation.setCreatedAt(carParkInformationPostgre.getCreatedAt());
        carParkInformation.setUpdatedAt(carParkInformationPostgre.getUpdatedAt());

        return carParkInformation;
    }
}
