package com.develab.carpark.adapter.input.rest.router;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class CarParkHealthHandler {

    public Mono<ServerResponse> checkHealth(ServerRequest serverRequest) {
        String notification = "Car Park Application is up";
        return ServerResponse.ok().bodyValue(notification);
    }
}
