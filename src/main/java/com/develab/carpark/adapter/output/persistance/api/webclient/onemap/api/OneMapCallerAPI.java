package com.develab.carpark.adapter.output.persistance.api.webclient.onemap.api;

import com.develab.carpark.adapter.output.persistance.api.webclient.onemap.properties.OneMapProperties;
import com.develab.carpark.adapter.output.persistance.api.webclient.onemap.request.ReverseGeoCodeRequest;
import com.develab.carpark.adapter.output.persistance.api.webclient.onemap.response.ReverseGeoCodeResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.Duration;

@Component
@Log4j2
public class OneMapCallerAPI implements OneMapCaller {

    private final WebClient oneMapWebClient;
    private final OneMapProperties properties;

    public OneMapCallerAPI(WebClient oneMapWebClient, OneMapProperties properties) {
        this.oneMapWebClient = oneMapWebClient;
        this.properties = properties;
    }
    @Override
    public Mono<ReverseGeoCodeResponse> callReverseGeoCode(ReverseGeoCodeRequest request) {
        URI uri = UriComponentsBuilder.newInstance()
                .path(properties.getReverseGeoPath())
                .queryParam("location", request.getXCoordinate()+","+request.getYCoordinate())
                .queryParam("buffer", properties.getBuffer())
                .build()
                .toUri();

        return exchange(RequestEntity.get(uri).build(), ReverseGeoCodeResponse.class)
                .onErrorResume(e-> {
                    log.info("Error Reverse Geo Code x : {} y {} : {}", request.getXCoordinate(), request.getYCoordinate(), e.getMessage());
                    return Mono.error(new Exception(e.getMessage()));
                });
    }

    private <T> Mono<T> exchange(RequestEntity<?> request, Class<T> responseClass) {
        return oneMapWebClient
                .method(request.getMethod())
                .uri(request.getUrl().toString())
                .retrieve()
                .bodyToMono(responseClass);
    }
}
