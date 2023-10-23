package com.develab.carpark.adapter.input.rest.converter;

import com.develab.carpark.adapter.input.rest.model.CarParkInformationCsv;
import com.develab.carpark.domain.entity.CarParkInformation;
import org.springframework.stereotype.Component;

@Component
public class CarParkInformationRestConverter {

    public CarParkInformation convertToDomain(CarParkInformationCsv carParkInformationCsv){
        CarParkInformation carParkInformation = new CarParkInformation();

        carParkInformation.setCarParkNumber(carParkInformationCsv.getCarParkNumber());
        carParkInformation.setAddress(carParkInformationCsv.getAddress());
        carParkInformation.setXCoordinate(Double.parseDouble(carParkInformationCsv.getXCoordinate()));
        carParkInformation.setYCoordinate(Double.parseDouble(carParkInformationCsv.getYCoordinate()));
        carParkInformation.setCarParkType(carParkInformationCsv.getCarParkType());
        carParkInformation.setParkingSystemType(carParkInformationCsv.getParkingSystemType());
        carParkInformation.setShortTermParking(carParkInformationCsv.getShortTermParking());
        carParkInformation.setFreeParking(convertFreeParking(carParkInformationCsv.getFreeParking()));
        carParkInformation.setNightParking(convertNightParking(carParkInformationCsv.getNightParking()));
        carParkInformation.setGantryHeight(Double.parseDouble(carParkInformationCsv.getGantryHeight()));
        carParkInformation.setCarParkBasement(convertCarParkBasement(carParkInformationCsv.getCarParkBasement()));

        return carParkInformation;

    }

    private boolean convertFreeParking(String freeParking){
        return "YES".equals(freeParking);
    }

    private boolean convertNightParking(String nightParking){
        return "YES".equals(nightParking);
    }

    private boolean convertCarParkBasement(String carParkBasement){
        return "Y".equals(carParkBasement);
    }
}
