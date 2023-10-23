package com.develab.carpark.adapter.output.persistance.api.webclient.govtsg;

import com.develab.carpark.adapter.output.persistance.api.webclient.govtsg.api.GovtSgCaller;
import com.develab.carpark.adapter.output.persistance.api.webclient.govtsg.converter.CarParkAvailabilityRestConverter;
import com.develab.carpark.adapter.output.persistance.api.webclient.govtsg.request.CarParkAvailabilityRequest;
import com.develab.carpark.adapter.output.persistance.api.webclient.govtsg.response.CarParkAvailabilityResponse;
import com.develab.carpark.application.port.output.CarParkAvailabilityCaller;
import com.develab.carpark.domain.entity.CarParkAvailability;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Log4j2
public class CarParkAvailabilityRestAdapter implements CarParkAvailabilityCaller {

    private final GovtSgCaller govtSgCaller;
    private final CarParkAvailabilityRestConverter carParkAvailabilityRestConverter;
    final static DateTimeFormatter ISO_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;


    public CarParkAvailabilityRestAdapter(GovtSgCaller govtSgCaller, CarParkAvailabilityRestConverter carParkAvailabilityRestConverter) {
        this.govtSgCaller = govtSgCaller;
        this.carParkAvailabilityRestConverter = carParkAvailabilityRestConverter;
    }

    @Override
    public Flux<CarParkAvailability> fetchCarParkAvailability(LocalDateTime dateTime) {
        String formattedDateTime = dateTime.format(ISO_FORMATTER);

        return govtSgCaller.fetchAvailability(new CarParkAvailabilityRequest(formattedDateTime))
                .flatMapMany(this::extractResponse);
    }

    private Flux<CarParkAvailability> extractResponse(CarParkAvailabilityResponse response){
        return Flux.fromIterable(response.getCarParkItems().get(0).getCarParkData())
                .map(carParkAvailabilityRestConverter::convertToEntity);

    }
}
