package com.develab.carpark.adapter.output.persistance.api.webclient.onemap;

import com.develab.carpark.adapter.output.persistance.api.webclient.onemap.api.OneMapCaller;
import com.develab.carpark.adapter.output.persistance.api.webclient.onemap.request.ReverseGeoCodeRequest;
import com.develab.carpark.application.port.output.CarParkInformationCaller;
import com.develab.carpark.domain.entity.CarParkInformation;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;


@Component
@Log4j2
public class CarParkInformationRestAdapter implements CarParkInformationCaller {

    private final OneMapCaller oneMapCaller;

    public CarParkInformationRestAdapter(OneMapCaller oneMapCaller) {
        this.oneMapCaller = oneMapCaller;
    }

    @Override
    public Mono<CarParkInformation> fetchGeoCode(CarParkInformation carParkInformation) {
        return oneMapCaller
                .callReverseGeoCode(convertRequest(carParkInformation.getXCoordinate(), carParkInformation.getYCoordinate()))
                .flatMap(response -> {
                    if (response.getGeoCodeInfo().size() != 0){
                        carParkInformation.setLatitude(Double.parseDouble(response.getGeoCodeInfo().get(0).getLatitude()));
                        carParkInformation.setLongitude(Double.parseDouble(response.getGeoCodeInfo().get(0).getLongitude()));
                    } else {
                        log.info("Unable to Reverse Geo Code {} location {},{}",
                                carParkInformation.getCarParkNumber(),
                                carParkInformation.getXCoordinate(),
                                carParkInformation.getYCoordinate());
                    }

                    return  Mono.just(carParkInformation);
                })
                ;
    }

    private ReverseGeoCodeRequest convertRequest(Double xCoordinate, Double yCoordiante){
        return new ReverseGeoCodeRequest(String.valueOf(xCoordinate), String.valueOf(yCoordiante));
    }
}
