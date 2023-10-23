package com.develab.carpark.adapter.output.persistance.postgre.carparkavailability;

import com.develab.carpark.adapter.output.persistance.postgre.carparkavailability.data.CarParkAvailabilityPostgre;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Repository
public interface CarParkAvailabilityR2DBCAdapter extends ReactiveCrudRepository<CarParkAvailabilityPostgre, BigDecimal> {

    Mono<CarParkAvailabilityPostgre> findByCarParkNumber(String carParkNumber);
}
