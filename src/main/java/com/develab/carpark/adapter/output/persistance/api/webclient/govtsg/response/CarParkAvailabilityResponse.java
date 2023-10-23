package com.develab.carpark.adapter.output.persistance.api.webclient.govtsg.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarParkAvailabilityResponse {

    @JsonProperty("items")
    private List<CarParkItemsResponse> carParkItems;
}
