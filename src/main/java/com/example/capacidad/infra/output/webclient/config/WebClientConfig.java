package com.example.capacidad.infra.output.webclient.config;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration

public class WebClientConfig {

    @Value("${bootcamp.service.url}")
    private String bootcampServiceUrl;

    @Value("${services.tecnologias.base-url}")
    private String tecnologiaServiceUrl;


    @Bean(name = "bootcampWebClient")
    public WebClient bootcampWebClient(WebClient.Builder builder){
        return builder.baseUrl(bootcampServiceUrl).build();
    }
    @Bean(name = "tecnologiaWebClient")
    public WebClient capacidadWebClient(WebClient.Builder builder) {
        return builder.baseUrl(tecnologiaServiceUrl).build();
    }
}





