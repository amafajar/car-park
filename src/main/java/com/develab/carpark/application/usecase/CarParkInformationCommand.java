package com.develab.carpark.application.usecase;

import com.develab.carpark.domain.entity.CarParkInformation;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CarParkInformationCommand {

    Mono<CarParkInformation> insertCarParkInformation(CarParkInformation carParkInformation);
    Flux<CarParkInformation> convertGeoCode();
    Flux<CarParkInformation> findConvertedCarParkInfo();
}
