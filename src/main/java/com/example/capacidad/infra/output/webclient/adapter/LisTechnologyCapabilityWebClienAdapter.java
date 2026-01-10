package com.example.capacidad.infra.output.webclient.adapter;


import com.example.capacidad.domain.model.listbootcamp.TecnologiaByBootSummary;
import com.example.capacidad.domain.ports.out.listbootcamp.ITechnologyCapaClientPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.List;

@Component
@RequiredArgsConstructor

public class LisTechnologyCapabilityWebClienAdapter implements ITechnologyCapaClientPort {

    private final WebClient tecnologiaWebClient;

    @Override
    public Flux<TecnologiaByBootSummary> findByIds(List<Long> ids) {

        return tecnologiaWebClient
                .post()
                .uri("/api/tecnologia/by-ids")
                .bodyValue(ids)
                .retrieve()
                .bodyToFlux(TecnologiaByBootSummary.class);


    }
}
