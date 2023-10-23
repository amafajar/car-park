package com.develab.carpark.application.usecase;

import com.develab.carpark.domain.entity.CarPark;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;

public interface CarParkCommand {
    Flux<CarPark> findNearestCarPark(double latitude, double longitude, Pageable pageable);
}
