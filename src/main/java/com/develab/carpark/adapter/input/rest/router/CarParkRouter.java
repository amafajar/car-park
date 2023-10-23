package com.develab.carpark.adapter.input.rest.router;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

import com.develab.carpark.adapter.input.rest.ContextPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

@Configuration
public class CarParkRouter {

    @Autowired
    private CarParkHandler carParkHandler;

    @Bean
    public RouterFunction carPark() {
        return RouterFunctions
                .route(GET(ContextPath.BASE_CONTEXT_PATH
                        .concat(ContextPath.VERSION_1)
                        .concat(ContextPath.CONTEXT_PATH_CAR_PARK)
                        .concat(ContextPath.CONTEXT_PATH_NEAREST))
                        .and(accept(MediaType.APPLICATION_JSON)), carParkHandler::findNearestCarPark);

    }
}
