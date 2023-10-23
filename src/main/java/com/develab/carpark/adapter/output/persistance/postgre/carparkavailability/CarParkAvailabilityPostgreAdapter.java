package com.develab.carpark.adapter.output.persistance.postgre.carparkavailability;

import com.develab.carpark.adapter.output.persistance.postgre.carparkavailability.converter.CarParkAvailabilityPostgreConverter;
import com.develab.carpark.application.port.output.CarParkAvailabilityDatabase;
import com.develab.carpark.domain.entity.CarParkAvailability;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

import static com.develab.carpark.util.EntityUtils.persistable;

@Repository
public class CarParkAvailabilityPostgreAdapter implements CarParkAvailabilityDatabase {

    private CarParkAvailabilityR2DBCAdapter carParkAvailabilityR2DBCAdapter;
    private CarParkAvailabilityPostgreConverter carParkAvailabilityPostgreConverter;

    public CarParkAvailabilityPostgreAdapter(CarParkAvailabilityR2DBCAdapter carParkAvailabilityR2DBCAdapter, CarParkAvailabilityPostgreConverter carParkAvailabilityPostgreConverter) {
        this.carParkAvailabilityR2DBCAdapter = carParkAvailabilityR2DBCAdapter;
        this.carParkAvailabilityPostgreConverter = carParkAvailabilityPostgreConverter;
    }

    @Override
    public Mono<CarParkAvailability> insertCarParkAvailabilty(CarParkAvailability carParkAvailability) {
        carParkAvailability.setCreatedAt(LocalDateTime.now());
        carParkAvailability.setUpdatedAt(LocalDateTime.now());

        return Mono.just(carParkAvailabilityPostgreConverter.convertToDto(carParkAvailability))
                .flatMap(carParkAvailabilityPostgre ->
                        carParkAvailabilityR2DBCAdapter.save(persistable(carParkAvailabilityPostgre, true)))
                .map(result -> carParkAvailabilityPostgreConverter.convertToEntity(result));
    }

    @Override
    public Mono<CarParkAvailability> updateCarParkAvailabilty(CarParkAvailability carParkAvailability) {
        carParkAvailability.setUpdatedAt(LocalDateTime.now());

        return Mono.just(carParkAvailabilityPostgreConverter.convertToDto(carParkAvailability))
                .flatMap(carParkAvailabilityPostgre ->
                        carParkAvailabilityR2DBCAdapter.save(carParkAvailabilityPostgre))
                .map(result -> carParkAvailabilityPostgreConverter.convertToEntity(result));
    }

    @Override
    public Mono<CarParkAvailability> findByCarParkNumber(String carParkNumber) {
        return carParkAvailabilityR2DBCAdapter.findByCarParkNumber(carParkNumber)
                .map(result -> carParkAvailabilityPostgreConverter.convertToEntity(result));
    }

    @Override
    public Flux<CarParkAvailability> findAll() {
        return carParkAvailabilityR2DBCAdapter.findAll()
                .map(result -> carParkAvailabilityPostgreConverter.convertToEntity(result));
    }
}
