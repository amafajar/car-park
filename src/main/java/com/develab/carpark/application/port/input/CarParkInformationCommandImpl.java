package com.develab.carpark.application.port.input;

import com.develab.carpark.application.port.output.CarParkInformationCaller;
import com.develab.carpark.application.port.output.CarParkInformationDatabase;
import com.develab.carpark.application.usecase.CarParkInformationCommand;
import com.develab.carpark.domain.entity.CarParkInformation;
import com.develab.carpark.domain.service.CarParkInformationService;
import com.develab.carpark.domain.valueobject.CarParkInformationStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Map;
import java.util.function.Function;

@Service
public class CarParkInformationCommandImpl implements CarParkInformationCommand {

    private CarParkInformationService carParkInformationService;
    private CarParkInformationDatabase carParkInformationDatabase;
    private CarParkInformationCaller carParkInformationCaller;

    public CarParkInformationCommandImpl(CarParkInformationService carParkInformationService,
                                         CarParkInformationDatabase carParkInformationDatabase, CarParkInformationCaller carParkInformationCaller) {
        this.carParkInformationService = carParkInformationService;
        this.carParkInformationDatabase = carParkInformationDatabase;
        this.carParkInformationCaller = carParkInformationCaller;
    }

    @Override
    public Mono<CarParkInformation> insertCarParkInformation(CarParkInformation carParkInformation) {
        return Mono.just(carParkInformation)
                    .map(info -> carParkInformationService.setStatusInfo(info, CarParkInformationStatus.RAW))
                    .flatMap(info -> carParkInformationDatabase.insertCarParkInformation(carParkInformation));
    }

    @Override
    public Flux<CarParkInformation> convertGeoCode() {
        return carParkInformationDatabase.findByStatus(CarParkInformationStatus.RAW.toString())
                .delayElements(Duration.ofMillis(300))
                .flatMap(carParkInformation -> carParkInformationCaller.fetchGeoCode(carParkInformation))
                .map(carParkInformation -> carParkInformationService.changeStatus(carParkInformation, CarParkInformationStatus.CONVERTED))
                .flatMap(carParkInformation -> carParkInformationDatabase.updateCarParkInformation(carParkInformation))
                ;
    }

    @Override
    public Flux<CarParkInformation> findConvertedCarParkInfo() {
        return carParkInformationDatabase.findByStatus(CarParkInformationStatus.CONVERTED.toString())
                .cache(Duration.ofSeconds(60));
    }

    @Override
    public Mono<Map<String, CarParkInformation>> fetchCarParkInformationMap() {
        return findConvertedCarParkInfo()
                .collectMap(CarParkInformation::getCarParkNumber, Function.identity());
    }
}
