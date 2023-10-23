package com.develab.carpark.adapter.output.persistance.api.webclient.govtsg.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarParkDataResponse {

    @JsonProperty("carpark_info")
    private List<CarParkInfoResponse> carParkInfo;
    @JsonProperty("carpark_number")
    private String carParkNumber;
    @JsonProperty("update_datetime")
    private String updateDatetime;

}
