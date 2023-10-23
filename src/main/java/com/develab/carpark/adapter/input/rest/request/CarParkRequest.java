package com.develab.carpark.adapter.input.rest.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarParkRequest {

    private String latitude;
    private String longitude;
    private String page;
    private String perPage;
}
