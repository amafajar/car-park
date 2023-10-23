package com.develab.carpark.application.port.output;

import com.develab.carpark.domain.entity.CarParkAvailability;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

public interface CarParkAvailabilityCaller {

    Flux<CarParkAvailability> fetchCarParkAvailability(LocalDateTime dateTime);
}
