package com.develab.carpark.adapter.output.persistance.api.webclient.govtsg.converter;

import com.develab.carpark.adapter.output.persistance.api.webclient.govtsg.response.CarParkAvailabilityResponse;
import com.develab.carpark.adapter.output.persistance.api.webclient.govtsg.response.CarParkDataResponse;
import com.develab.carpark.domain.entity.CarParkAvailability;
import org.springframework.stereotype.Component;

@Component
public class CarParkAvailabilityRestConverter {

    public CarParkAvailability convertToEntity(CarParkDataResponse response){
        CarParkAvailability carParkAvailability = new CarParkAvailability();

        carParkAvailability.setCarParkNumber(response.getCarParkNumber());
        carParkAvailability.setLotType(response.getCarParkInfo().get(0).getLotType());
        carParkAvailability.setTotalLots(Integer.parseInt(response.getCarParkInfo().get(0).getTotalLots()));
        carParkAvailability.setAvailableLots(Integer.parseInt(response.getCarParkInfo().get(0).getAvailableLots()));

        return carParkAvailability;
    }
}
