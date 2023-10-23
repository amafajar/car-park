package com.develab.carpark.adapter.input.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarParkResponse {

    private String address;
    private double latitude;
    private double longitude;
    private double distance;
    @JsonProperty("total_lots")
    private int totalLots;
    @JsonProperty("available_lots")
    private int availableLots;
}
