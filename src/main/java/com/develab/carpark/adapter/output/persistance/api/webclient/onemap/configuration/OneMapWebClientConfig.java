package com.develab.carpark.adapter.output.persistance.api.webclient.onemap.configuration;


import com.develab.carpark.adapter.output.persistance.api.webclient.onemap.properties.OneMapProperties;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class OneMapWebClientConfig {

    private OneMapProperties properties;

    public OneMapWebClientConfig(OneMapProperties properties) {
        this.properties = properties;
    }

    @Bean
    public WebClient oneMapAuthWebClient(){
        return WebClient.builder()
                .baseUrl(properties.getBaseUrl())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
    @Bean
    public WebClient oneMapWebClient(@Qualifier("oneMapAuthExchangeFilter")ExchangeFilterFunction oneMapAuthExchangeFunction){
        return WebClient.builder()
                .filter(oneMapAuthExchangeFunction)
                .baseUrl(properties.getBaseUrl())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}
