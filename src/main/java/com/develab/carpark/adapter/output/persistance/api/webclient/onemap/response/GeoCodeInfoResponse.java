package com.develab.carpark.adapter.output.persistance.api.webclient.onemap.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeoCodeInfoResponse {

    @JsonProperty("BUILDINGNAME")
    private String buildingName;
    @JsonProperty("BLOCK")
    private String block;
    @JsonProperty("ROAD")
    private String road;
    @JsonProperty("POSTALCODE")
    private String postalCode;
    @JsonProperty("XCOORD")
    private String xCoordinate;
    @JsonProperty("YCOORD")
    private String yCoordinate;
    @JsonProperty("LATITUDE")
    private String latitude;
    @JsonProperty("LONGITUDE")
    private String longitude;

}
