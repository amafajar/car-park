package com.develab.carpark.adapter.output.persistance.api.webclient.onemap.authorization;

import com.develab.carpark.adapter.output.persistance.api.webclient.onemap.properties.OneMapProperties;
import com.develab.carpark.adapter.output.persistance.api.webclient.onemap.request.OneMapAuthorizationRequest;
import com.develab.carpark.adapter.output.persistance.api.webclient.onemap.response.OneMapAuthorizationResponse;
import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Objects;

@Component
@Log4j2
public class OneMapAuthorizationAPI implements OneMapAuthorization{

    private final WebClient webClient;
    private final OneMapProperties properties;

    private final Gson gson = new Gson();

    public OneMapAuthorizationAPI(WebClient oneMapAuthWebClient, OneMapProperties properties) {
        this.webClient = oneMapAuthWebClient;
        this.properties = properties;
    }

    @Override
    public Mono<OneMapAuthorizationResponse> authorize(OneMapAuthorizationRequest request) {
        URI uri = URI.create(properties.getTokenPath());

        return exchange(RequestEntity.post(uri).body(request), OneMapAuthorizationResponse.class)
                .flatMap(response -> {
                    log.info("request OneMap Auth: {}", gson.toJson(request));
                    log.info("response OneMap Auth: {}", gson.toJson(response));

                    return Mono.just(response);
                })
                .switchIfEmpty(Mono.error(new RuntimeException("Empty Response")));
    }
    private <T> Mono<T> exchange(RequestEntity<?> request, Class<T> responseClass) {
        return webClient
                .method(Objects.requireNonNull(request.getMethod()))
                .uri(request.getUrl().getPath())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(Objects.requireNonNull(request.getBody()))
                .retrieve()
                .bodyToMono(responseClass);
    }



}
