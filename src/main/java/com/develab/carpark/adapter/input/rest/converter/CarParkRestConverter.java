package com.develab.carpark.adapter.input.rest.converter;

import com.develab.carpark.adapter.input.rest.response.CarParkResponse;
import com.develab.carpark.domain.entity.CarPark;
import org.springframework.stereotype.Component;

@Component
public class CarParkRestConverter {

    public CarParkResponse convertToResponse(CarPark carPark){
        CarParkResponse response = new CarParkResponse();

        response.setAddress(carPark.getCarParkInformation().getAddress());
        response.setLatitude(carPark.getCarParkInformation().getLatitude());
        response.setLongitude(carPark.getCarParkInformation().getLongitude());
        response.setDistance(carPark.getDistance());
        response.setTotalLots(carPark.getCarParkAvailability().getTotalLots());
        response.setAvailableLots(carPark.getCarParkAvailability().getAvailableLots());

        return response;
    }
}
