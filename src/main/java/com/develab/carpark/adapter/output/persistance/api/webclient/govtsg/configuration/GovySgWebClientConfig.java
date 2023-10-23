package com.develab.carpark.adapter.output.persistance.api.webclient.govtsg.configuration;

import com.develab.carpark.adapter.output.persistance.api.webclient.govtsg.properties.GovtSgProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class GovySgWebClientConfig {

    private GovtSgProperties properties;

    public GovySgWebClientConfig(GovtSgProperties properties) {
        this.properties = properties;
    }

    @Bean
    public WebClient govtSgWebClient(){
        ExchangeStrategies strategies = ExchangeStrategies.builder()
                .codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(2 * 1024 * 1024))
                .build();

        return WebClient.builder()
                .exchangeStrategies(strategies)
                .baseUrl(properties.getBaseUrl())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}
