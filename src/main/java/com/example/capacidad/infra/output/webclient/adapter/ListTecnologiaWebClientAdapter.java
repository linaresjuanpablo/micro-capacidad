package com.example.capacidad.infra.output.webclient.adapter;

import com.example.capacidad.domain.model.TecnologiaSummary;
import com.example.capacidad.domain.ports.out.IListTecnologiaClientPort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.List;

@Component
//@RequiredArgsConstructor

public class ListTecnologiaWebClientAdapter implements IListTecnologiaClientPort {

    private final WebClient tecnologiaWebClient;

    public ListTecnologiaWebClientAdapter(@Qualifier("tecnologiaWebClient") WebClient tecnologiaWebClient){
        this.tecnologiaWebClient = tecnologiaWebClient;
    }


    @Value("${services.tecnologias.base-url}")
    private String tecnologiaBaseUrl;
    @Override
    public Flux<TecnologiaSummary> findByIds(List<Long> ids) {
        return tecnologiaWebClient
                .post()
                .uri("/tecnologia/by-ids")
                .bodyValue(ids)
                .retrieve()
                .bodyToFlux(TecnologiaSummary.class);
    }
}
