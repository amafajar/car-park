package com.develab.carpark.adapter.output.persistance.api.webclient.onemap.api;

import com.develab.carpark.adapter.output.persistance.api.webclient.onemap.request.ReverseGeoCodeRequest;
import com.develab.carpark.adapter.output.persistance.api.webclient.onemap.response.ReverseGeoCodeResponse;
import reactor.core.publisher.Mono;

public interface OneMapCaller {

    Mono<ReverseGeoCodeResponse> callReverseGeoCode(ReverseGeoCodeRequest request);
}
