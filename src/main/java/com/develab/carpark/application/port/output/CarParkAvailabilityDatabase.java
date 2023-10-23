package com.develab.carpark.application.port.output;

import com.develab.carpark.domain.entity.CarParkAvailability;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CarParkAvailabilityDatabase {

    Mono<CarParkAvailability> insertCarParkAvailabilty(CarParkAvailability carParkAvailability);
    Mono<CarParkAvailability> updateCarParkAvailabilty(CarParkAvailability carParkAvailability);
    Mono<CarParkAvailability> findByCarParkNumber(String carParkNumber);
    Flux<CarParkAvailability> findAll();
}
