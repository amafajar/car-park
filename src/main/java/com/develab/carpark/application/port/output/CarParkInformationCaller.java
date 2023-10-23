package com.develab.carpark.application.port.output;

import com.develab.carpark.domain.entity.CarParkInformation;
import reactor.core.publisher.Mono;

public interface CarParkInformationCaller {

    Mono<CarParkInformation> fetchGeoCode(CarParkInformation carParkInformation);
}
