package com.develab.carpark.adapter.input.rest.router;

import com.develab.carpark.application.usecase.CarParkAvailabilityCommand;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class CarParkAvailabilityHandler {

    private CarParkAvailabilityCommand carParkAvailabilityCommand;

    public Mono<ServerResponse> fetchCarAvailability(ServerRequest serverRequest){
        return carParkAvailabilityCommand.fetchCarParkAvailabilty()
                .collectList()
                .flatMap(carParkAvailabilityList -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(carParkAvailabilityList));
    }
}
