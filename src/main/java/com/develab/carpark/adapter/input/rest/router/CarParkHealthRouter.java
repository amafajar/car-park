package com.develab.carpark.adapter.input.rest.router;

import com.develab.carpark.adapter.input.rest.ContextPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class CarParkHealthRouter {

    @Autowired
    private CarParkHealthHandler carParkHealthHandler;

    @Bean
    public RouterFunction carParkHealth() {
        return RouterFunctions
                .route(GET(ContextPath.BASE_CONTEXT_PATH
                        .concat(ContextPath.VERSION_1)
                        .concat(ContextPath.CONTEXT_PATH_CAR_PARK_HEALTHCHECK))
                        .and(accept(MediaType.APPLICATION_JSON)), carParkHealthHandler::checkHealth);

    }
}
