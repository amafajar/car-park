package com.develab.carpark.application.usecase;

import com.develab.carpark.domain.entity.CarParkAvailability;
import reactor.core.publisher.Flux;

public interface CarParkAvailabilityCommand {

    Flux<CarParkAvailability> fetchCarParkAvailabilty();

    Flux<CarParkAvailability> findCarParkAvailabilty();
}
