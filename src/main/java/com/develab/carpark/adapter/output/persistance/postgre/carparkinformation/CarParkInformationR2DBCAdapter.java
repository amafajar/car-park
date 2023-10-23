package com.develab.carpark.adapter.output.persistance.postgre.carparkinformation;

import com.develab.carpark.adapter.output.persistance.postgre.carparkinformation.data.CarParkInformationPostgre;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Repository
public interface CarParkInformationR2DBCAdapter extends ReactiveCrudRepository<CarParkInformationPostgre, BigDecimal> {
    Flux<CarParkInformationPostgre> findByStatus(String status);
}
