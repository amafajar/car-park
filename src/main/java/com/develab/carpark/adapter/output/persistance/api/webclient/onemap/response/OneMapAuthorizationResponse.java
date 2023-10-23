package com.develab.carpark.adapter.output.persistance.api.webclient.onemap.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OneMapAuthorizationResponse {

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("expiry_timestamp")
    private long expiryTimestamp;
}
