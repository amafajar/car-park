package com.develab.carpark.adapter.input.rest.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarParkInformationRequest {

    private String locationCsv;
}
