package com.develab.carpark.adapter.output.persistance.api.webclient.govtsg.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarParkItemsResponse {

    @JsonProperty("timestamp")
    private String timestamp;
    @JsonProperty("carpark_data")
    private List<CarParkDataResponse> carParkData;

}
