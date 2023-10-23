package com.develab.carpark.adapter.input.rest.router;

import com.develab.carpark.adapter.input.rest.converter.CarParkInformationRestConverter;
import com.develab.carpark.adapter.input.rest.model.CarParkInformationCsv;
import com.develab.carpark.adapter.input.rest.request.CarParkInformationRequest;
import com.develab.carpark.application.usecase.CarParkInformationCommand;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.List;

@Component
@AllArgsConstructor
public class CarParkInformationHandler {

    private CarParkInformationRestConverter carParkInformationRestConverter;
    private CarParkInformationCommand carParkInformationCommand;

    public Mono<ServerResponse> fetchCarParkInformation(ServerRequest serverRequest){
        return serverRequest.bodyToMono(CarParkInformationRequest.class)
                .flatMapMany(request -> fetchConvertFromCsv(request.getLocationCsv()))
                .map(carParkInformationCsv -> carParkInformationRestConverter.convertToDomain(carParkInformationCsv))
                .flatMap(carParkInformation -> carParkInformationCommand.insertCarParkInformation(carParkInformation))
                .then(ServerResponse
                        .status(HttpStatus.CREATED)
                        .bodyValue("CSV has been inserted into car park information"))
                ;
    }

    public Mono<ServerResponse> convertCarParkInformation(ServerRequest serverRequest){
        return carParkInformationCommand.convertGeoCode()
                .collectList()
                .flatMap(convertedCarParkInfoList ->{
                    return ServerResponse.ok()
                            .contentType(MediaType.APPLICATION_JSON)
                            .bodyValue(convertedCarParkInfoList);
                });
    }

    private Flux<CarParkInformationCsv> fetchConvertFromCsv(String fileName){
        Path filePath = Path.of("src/main/resources/csv/" + fileName);

        List<CarParkInformationCsv> carParkInformationCsvList;
        try {
            carParkInformationCsvList =
                    new CsvToBeanBuilder(new FileReader(filePath.toUri().getPath()))
                            .withType(CarParkInformationCsv.class)
                            .build()
                            .parse();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return Flux.fromIterable(carParkInformationCsvList)
                .doOnNext(System.out::println);
    }
}
