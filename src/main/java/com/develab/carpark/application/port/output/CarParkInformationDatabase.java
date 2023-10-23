package com.develab.carpark.application.port.output;

import com.develab.carpark.domain.entity.CarParkInformation;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CarParkInformationDatabase {

    Mono<CarParkInformation> insertCarParkInformation(CarParkInformation carParkInformation);
    Mono<CarParkInformation> updateCarParkInformation(CarParkInformation carParkInformation);

    Flux<CarParkInformation> findByStatus(String status);
}
