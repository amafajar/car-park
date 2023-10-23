package com.develab.carpark.service;

import com.develab.carpark.domain.service.CarParkService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CarParkServiceTest {
    private CarParkService carParkService;
    @BeforeEach
    void init() {
        carParkService = new CarParkService();
    }

    @Test
    void givenPairGeoCode_whenCalculateDistance_thenReturnValidDistance() {
        double originLatitude = 1.321094174025142;
        double originLongitude = 103.88505706589773;
        double targetLatitude = 1.3197588;
        double targetLongitude = 103.8862991;

        StepVerifier.create(
                Mono.just(carParkService.calculateDistance(originLatitude,
                        originLongitude,
                        targetLatitude,
                        targetLongitude)))
                .consumeNextWith(distance -> assertEquals(202.76, distance))
                .verifyComplete();
    }
}
