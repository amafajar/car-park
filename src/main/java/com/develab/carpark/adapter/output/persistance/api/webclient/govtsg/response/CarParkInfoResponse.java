package com.develab.carpark.adapter.output.persistance.api.webclient.govtsg.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarParkInfoResponse {

    @JsonProperty("total_lots")
    private String totalLots;
    @JsonProperty("lot_type")
    private String lotType;
    @JsonProperty("lots_available")
    private String availableLots;
}
