package com.develab.carpark.adapter.output.persistance.api.webclient.govtsg.api;

import com.develab.carpark.adapter.output.persistance.api.webclient.govtsg.request.CarParkAvailabilityRequest;
import com.develab.carpark.adapter.output.persistance.api.webclient.govtsg.response.CarParkAvailabilityResponse;
import reactor.core.publisher.Mono;

public interface GovtSgCaller {

    Mono<CarParkAvailabilityResponse> fetchAvailability(CarParkAvailabilityRequest request);
}
