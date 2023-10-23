package com.develab.carpark.adapter.output.persistance.api.webclient.onemap.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReverseGeoCodeRequest {

    private String xCoordinate;
    private String yCoordinate;
}
