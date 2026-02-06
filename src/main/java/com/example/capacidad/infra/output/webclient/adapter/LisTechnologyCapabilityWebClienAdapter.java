package com.example.capacidad.infra.output.webclient.adapter;


import com.example.capacidad.domain.model.listbootcamp.TecnologiaByBootSummary;
import com.example.capacidad.domain.ports.out.listbootcamp.ITechnologyCapaClientPort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.List;

@Component

public class LisTechnologyCapabilityWebClienAdapter implements ITechnologyCapaClientPort {
    private final WebClient tecnologiaWebClient;

    public LisTechnologyCapabilityWebClienAdapter(@Qualifier("tecnologiaWebClient") WebClient tecnologiaWebClient) {
        this.tecnologiaWebClient = tecnologiaWebClient;
    }
    @Override
    public Flux<TecnologiaByBootSummary> findByIds(List<Long> ids) {

        return tecnologiaWebClient
                .post()
                .uri("/tecnologia/by-ids")
                .bodyValue(ids)
                .retrieve()
                .bodyToFlux(TecnologiaByBootSummary.class);


    }
}
