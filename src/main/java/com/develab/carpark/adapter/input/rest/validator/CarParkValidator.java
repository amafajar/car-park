package com.develab.carpark.adapter.input.rest.validator;

import com.develab.carpark.adapter.input.rest.request.CarParkRequest;
import org.springframework.stereotype.Component;

@Component
public class CarParkValidator {

    public void validate(CarParkRequest request){
        if (request.getLatitude() == null){
            throw new ValidationException("latitude", "Please Input Latitude");
        }
        if (request.getLongitude() == null){
            throw new ValidationException("longitude", "Please Input Longitude");
        }
        if (Integer.parseInt(request.getPage()) <= 0){
            throw new ValidationException("page", "Page start from 1");
        }
        if (Integer.parseInt(request.getPerPage()) <= 0){
            throw new ValidationException("perPage", "Page size must not be less than 1");
        }

    }
}
