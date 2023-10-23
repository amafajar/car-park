package com.develab.carpark.application.port.input;

import com.develab.carpark.application.port.output.CarParkAvailabilityCaller;
import com.develab.carpark.application.port.output.CarParkAvailabilityDatabase;
import com.develab.carpark.application.usecase.CarParkAvailabilityCommand;
import com.develab.carpark.application.usecase.CarParkInformationCommand;
import com.develab.carpark.domain.entity.CarParkAvailability;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class CarParkAvailabilityCommandImpl implements CarParkAvailabilityCommand {

    private CarParkAvailabilityDatabase carParkAvailabilityDatabase;
    private CarParkAvailabilityCaller carParkAvailabilityCaller;

    public CarParkAvailabilityCommandImpl(CarParkAvailabilityDatabase carParkAvailabilityDatabase,
                                          CarParkAvailabilityCaller carParkAvailabilityCaller) {
        this.carParkAvailabilityDatabase = carParkAvailabilityDatabase;
        this.carParkAvailabilityCaller = carParkAvailabilityCaller;
    }

    @Override
    public Flux<CarParkAvailability> fetchCarParkAvailabilty() {
        return carParkAvailabilityCaller.fetchCarParkAvailability(LocalDateTime.now())
                .flatMap(carParkAvailability ->
                        carParkAvailabilityDatabase.findByCarParkNumber(carParkAvailability.getCarParkNumber())
                                .flatMap(entity -> {
                                    entity.setLotType(carParkAvailability.getLotType());
                                    entity.setTotalLots(carParkAvailability.getTotalLots());
                                    entity.setAvailableLots(carParkAvailability.getAvailableLots());

                                    return carParkAvailabilityDatabase.updateCarParkAvailabilty(entity);
                                })
                                .switchIfEmpty(Mono.defer(() -> carParkAvailabilityDatabase.insertCarParkAvailabilty(carParkAvailability)))
                );
    }

    @Override
    public Flux<CarParkAvailability> findCarParkAvailabilty() {
        return carParkAvailabilityDatabase.findAll()
                .cache(Duration.ofSeconds(60));
    }
}
