package com.develab.carpark.adapter.output.persistance.api.webclient.onemap.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "onemap")
public class OneMapProperties {

    private String baseUrl;
    private String tokenPath;
    private String reverseGeoPath;
    private int buffer;
    private String email;
    private String password;
    private int timeout;
}
