package com.develab.carpark.adapter.input.rest.router;

import com.develab.carpark.adapter.input.rest.ContextPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class CarParkInformationRouter {

    @Autowired
    private CarParkInformationHandler carParkInformationHandler;
    @Bean
    public RouterFunction carParkInformation() {
        return RouterFunctions
                .route(POST(ContextPath.BASE_CONTEXT_PATH
                        .concat(ContextPath.VERSION_1)
                        .concat(ContextPath.CONTEXT_PATH_CAR_PARK_INFORMATION))
                        .and(accept(MediaType.APPLICATION_JSON)), carParkInformationHandler::fetchCarParkInformation)
                .andRoute(GET(ContextPath.BASE_CONTEXT_PATH
                        .concat(ContextPath.VERSION_1)
                        .concat(ContextPath.CONTEXT_PATH_CAR_PARK_INFORMATION)
                        .concat(ContextPath.CONTEXT_PATH_GEOCODE))
                        .and(accept(MediaType.APPLICATION_JSON)), carParkInformationHandler::convertCarParkInformation);
    }
}
