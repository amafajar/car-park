package com.develab.carpark.application.port.input;

import com.develab.carpark.application.usecase.CarParkAvailabilityCommand;
import com.develab.carpark.application.usecase.CarParkCommand;
import com.develab.carpark.application.usecase.CarParkInformationCommand;
import com.develab.carpark.domain.entity.CarPark;
import com.develab.carpark.domain.entity.CarParkAvailability;
import com.develab.carpark.domain.entity.CarParkInformation;
import com.develab.carpark.domain.service.CarParkService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CarParkCommandImpl implements CarParkCommand {

    private CarParkInformationCommand carParkInformationCommand;
    private CarParkAvailabilityCommand carParkAvailabilityCommand;
    private CarParkService carParkService;

    public CarParkCommandImpl(CarParkInformationCommand carParkInformationCommand,
                              CarParkAvailabilityCommand carParkAvailabilityCommand, CarParkService carParkService) {
        this.carParkInformationCommand = carParkInformationCommand;
        this.carParkAvailabilityCommand = carParkAvailabilityCommand;
        this.carParkService = carParkService;
    }

    @Override
    public Flux<CarPark> findNearestCarPark(double latitude, double longitude, Pageable pageable) {
        return carParkInformationCommand.fetchCarParkInformationMap()
                .flatMapMany(carParkInfoMap -> carParkAvailabilityCommand.findCarParkAvailabilty()
                        .filter(carParkAvailability -> carParkAvailability.getAvailableLots() != 0)
                        .flatMap(carParkAvailability -> {
                            CarParkInformation carParkInformation = carParkInfoMap.get(carParkAvailability.getCarParkNumber());

                            if (carParkInformation != null) {
                                CarPark carPark = constructCarPark(
                                        carParkInfoMap.get(carParkAvailability.getCarParkNumber()),
                                        carParkAvailability,
                                        latitude,
                                        longitude);
                                return Mono.just(carPark);
                            } else {
                                return Mono.empty();
                            }
                        })
                )
                .sort(carParkService.sortCarPark())
                .skip(pageable.getOffset())
                .take(pageable.getPageSize());
    }

    private double calculateDisctance(CarParkInformation carParkInformation, double latitude, double longitude) {
        return carParkService.calculateDistance(latitude,
                longitude,
                carParkInformation.getLatitude(),
                carParkInformation.getLongitude());
    }

    private CarPark constructCarPark(CarParkInformation carParkInformation, CarParkAvailability carParkAvailability, double latitude, double longitude){
        return new CarPark(
                carParkInformation,
                carParkAvailability,
                calculateDisctance(carParkInformation, latitude, longitude));
    }
}
