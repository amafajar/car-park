package com.develab.carpark.adapter.output.persistance.api.webclient.onemap.authorization;

import com.develab.carpark.adapter.output.persistance.api.webclient.onemap.request.OneMapAuthorizationRequest;
import com.develab.carpark.adapter.output.persistance.api.webclient.onemap.response.OneMapAuthorizationResponse;
import reactor.core.publisher.Mono;

public interface OneMapAuthorization {

    Mono<OneMapAuthorizationResponse> authorize(OneMapAuthorizationRequest authorizationRequest);
}
