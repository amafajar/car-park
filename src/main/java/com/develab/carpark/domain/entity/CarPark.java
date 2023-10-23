package com.develab.carpark.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarPark {

    private CarParkInformation carParkInformation;
    private CarParkAvailability carParkAvailability;
    private Double distance;
}
