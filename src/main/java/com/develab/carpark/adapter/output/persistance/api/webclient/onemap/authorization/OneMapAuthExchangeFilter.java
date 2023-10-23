package com.develab.carpark.adapter.output.persistance.api.webclient.onemap.authorization;

import com.develab.carpark.adapter.output.persistance.api.webclient.onemap.properties.OneMapProperties;
import com.develab.carpark.adapter.output.persistance.api.webclient.onemap.request.OneMapAuthorizationRequest;
import com.develab.carpark.adapter.output.persistance.api.webclient.onemap.response.OneMapAuthorizationResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;

@Component
@Log4j2
public class OneMapAuthExchangeFilter implements ExchangeFilterFunction {

    private final Mono<String> authorization;

    public OneMapAuthExchangeFilter(OneMapAuthorizationAPI authorizationAPI, OneMapProperties properties) {
        authorization = authorizationAPI.authorize(
                        new OneMapAuthorizationRequest(properties.getEmail(), properties.getPassword()))
                .map(OneMapAuthorizationResponse::getAccessToken)
                .retryWhen(Retry.fixedDelay(3, Duration.ofMillis(1500)))
                .doOnNext(token -> log.info("[One Map Auth] token '{}'", token))
                .cache(Duration.ofHours(1))
                .doOnError(err -> log.error("[One Map Auth] Failed " + err.getMessage(), err));
    }

    @Override
    public Mono<ClientResponse> filter(ClientRequest request, ExchangeFunction next) {
        return authorization
                .flatMap(authorizationId -> next.exchange(ClientRequest
                        .from(request)
                        .header(HttpHeaders.AUTHORIZATION, authorizationId)
                        .build()))
                .onErrorResume(err -> {
                    log.error("[One Map Auth] Filter Function", err);
                    return Mono.error(err);
                });
    }
}
