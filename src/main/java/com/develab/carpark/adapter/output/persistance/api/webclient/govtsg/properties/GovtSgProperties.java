package com.develab.carpark.adapter.output.persistance.api.webclient.govtsg.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "govtsg")
public class GovtSgProperties {
    private String baseUrl;
    private String carParkAvailabiltyPath;

}
