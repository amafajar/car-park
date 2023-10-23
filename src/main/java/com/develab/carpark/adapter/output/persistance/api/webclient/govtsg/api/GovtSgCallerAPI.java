package com.develab.carpark.adapter.output.persistance.api.webclient.govtsg.api;

import com.develab.carpark.adapter.output.persistance.api.webclient.govtsg.properties.GovtSgProperties;
import com.develab.carpark.adapter.output.persistance.api.webclient.govtsg.request.CarParkAvailabilityRequest;
import com.develab.carpark.adapter.output.persistance.api.webclient.govtsg.response.CarParkAvailabilityResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.net.URI;
import java.time.Duration;

@Component
@Log4j2
public class GovtSgCallerAPI implements GovtSgCaller{

    private final WebClient govtSgWebClient;

    private final GovtSgProperties properties;

    public GovtSgCallerAPI(WebClient govtSgWebClient, GovtSgProperties properties) {
        this.govtSgWebClient = govtSgWebClient;
        this.properties = properties;
    }

    @Override
    public Mono<CarParkAvailabilityResponse> fetchAvailability(CarParkAvailabilityRequest request) {
        log.info("[API Call] GovtSg Availability with request: {}", request.getTimestamp());

        URI uri = UriComponentsBuilder.newInstance()
                .path(properties.getCarParkAvailabiltyPath())
                .queryParam("timestamp", request.getTimestamp())
                .build()
                .toUri();

        return exchange(RequestEntity.get(uri).build(), CarParkAvailabilityResponse.class)
                .onErrorResume(e-> {
                    log.info("Unable to fetch Car Park Availability timestamp {} : {}", request.getTimestamp(), e.getMessage());
                    return Mono.error(new Exception(e.getMessage()));
                })
                .retryWhen(Retry.fixedDelay(5, Duration.ofSeconds(3)));
    }

    private <T> Mono<T> exchange(RequestEntity<?> request, Class<T> responseClass) {
        return govtSgWebClient
                .method(request.getMethod())
                .uri(request.getUrl().toString())
                .retrieve()
                .bodyToMono(responseClass);
    }
}
