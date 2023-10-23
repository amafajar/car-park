package com.develab.carpark.adapter.input.rest.router;

import com.develab.carpark.adapter.input.rest.converter.CarParkRestConverter;
import com.develab.carpark.adapter.input.rest.request.CarParkRequest;
import com.develab.carpark.adapter.input.rest.validator.CarParkValidator;
import com.develab.carpark.adapter.input.rest.validator.ValidationException;
import com.develab.carpark.application.usecase.CarParkCommand;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@Log4j2
public class CarParkHandler {

    private final CarParkValidator validator;
    private final CarParkCommand carParkCommand;
    private final CarParkRestConverter carParkRestConverter;
    private static String DEFAULT_PAGE = "1";
    private static String DEFAULT_PER_PAGE = "10";

    public CarParkHandler(CarParkValidator validator, CarParkCommand carParkCommand, CarParkRestConverter carParkRestConverter) {
        this.validator = validator;
        this.carParkCommand = carParkCommand;
        this.carParkRestConverter = carParkRestConverter;
    }

    public Mono<ServerResponse> findNearestCarPark(ServerRequest serverRequest) {
        return extractRequest(serverRequest)
                .doOnNext(validator::validate)
                .flatMapMany(carParkRequest ->
                        carParkCommand.findNearestCarPark(
                                Double.parseDouble(carParkRequest.getLatitude()),
                                Double.parseDouble(carParkRequest.getLongitude()),
                                extractPageable(carParkRequest)))
                .map(carParkRestConverter::convertToResponse)
                .collectList()
                .flatMap(response -> ServerResponse.ok().bodyValue(response))
                .onErrorResume(e -> {
                    if (e instanceof ValidationException){
                        return ServerResponse.badRequest().bodyValue(((ValidationException) e).getErrorMessage());
                    }

                    return Mono.error(e);
                });
    }

    private Mono<CarParkRequest> extractRequest(ServerRequest serverRequest){
        CarParkRequest carParkRequest = new CarParkRequest(
                serverRequest.queryParam("latitude").orElse(null),
                serverRequest.queryParam("longitude").orElse(null),
                serverRequest.queryParam("page").orElse(DEFAULT_PAGE),
                serverRequest.queryParam("per_page").orElse(DEFAULT_PER_PAGE)
        );

        log.info("API Nearest Car Park request : {}",carParkRequest.toString());
        return  Mono.just(carParkRequest);
    }

    private Pageable extractPageable(CarParkRequest carParkRequest){
        return PageRequest.of(Integer.parseInt(carParkRequest.getPage())-1, Integer.parseInt(carParkRequest.getPerPage()));
    }

}
