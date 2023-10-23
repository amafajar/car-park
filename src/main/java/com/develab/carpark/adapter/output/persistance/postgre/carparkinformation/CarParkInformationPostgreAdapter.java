package com.develab.carpark.adapter.output.persistance.postgre.carparkinformation;

import com.develab.carpark.adapter.output.persistance.postgre.carparkinformation.converter.CarParkInformationPostgreConverter;
import com.develab.carpark.application.port.output.CarParkInformationDatabase;
import com.develab.carpark.domain.entity.CarParkInformation;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.develab.carpark.util.EntityUtils.persistable;

@Repository
public class CarParkInformationPostgreAdapter implements CarParkInformationDatabase {

    private CarParkInformationR2DBCAdapter carParkInformationR2DBCAdapter;
    private CarParkInformationPostgreConverter carParkInformationPostgreConverter;

    public CarParkInformationPostgreAdapter(CarParkInformationR2DBCAdapter carParkInformationR2DBCAdapter,
                                            CarParkInformationPostgreConverter carParkInformationPostgreConverter) {
        this.carParkInformationR2DBCAdapter = carParkInformationR2DBCAdapter;
        this.carParkInformationPostgreConverter = carParkInformationPostgreConverter;
    }

    @Override
    public Mono<CarParkInformation> insertCarParkInformation(CarParkInformation carParkInformation) {
        return Mono.just(carParkInformationPostgreConverter.convertToDto(carParkInformation))
                .flatMap(carParkInformationPostgre ->
                        carParkInformationR2DBCAdapter.save(persistable(carParkInformationPostgre, true)))
                .map(result -> carParkInformationPostgreConverter.convertToEntity(result));
    }

    @Override
    public Mono<CarParkInformation> updateCarParkInformation(CarParkInformation carParkInformation) {
        return Mono.just(carParkInformationPostgreConverter.convertToDto(carParkInformation))
                .flatMap(carParkInformationPostgre ->
                        carParkInformationR2DBCAdapter.save(carParkInformationPostgre))
                .map(result -> carParkInformationPostgreConverter.convertToEntity(result));
    }

    @Override
    public Flux<CarParkInformation> findByStatus(String status) {
        return carParkInformationR2DBCAdapter.findByStatus(status)
                .map(result -> carParkInformationPostgreConverter.convertToEntity(result));
    }
}
