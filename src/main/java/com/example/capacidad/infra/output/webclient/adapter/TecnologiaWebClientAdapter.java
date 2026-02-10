package com.example.capacidad.infra.output.webclient.adapter;

import com.example.capacidad.domain.model.TecnologiaSummary;
import com.example.capacidad.domain.ports.out.ICapabilityRepositoryPor;
import com.example.capacidad.domain.ports.out.ITecnologiaClientPort;
import com.example.capacidad.infra.input.controller.TechnologyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@RequiredArgsConstructor

public class TecnologiaWebClientAdapter implements ITecnologiaClientPort {

    private final WebClient tecnologiaWebClient;

    @Override
    public Mono<Boolean> existsById(Long tecnologiaId) {
        return tecnologiaWebClient
                .post()
                //.uri("/tecnologia/{id}", tecnologiaId)
                .uri("/tecnologia/by-ids", tecnologiaId)
                .bodyValue(List.of(tecnologiaId))
                .retrieve()
                .onStatus(HttpStatus.NOT_FOUND::equals, response -> Mono.empty())
                .toBodilessEntity()
                .map(r-> true)
                .defaultIfEmpty(false);
    }

    @Override
    public Flux<TecnologiaSummary> findByIds(List<Long> ids) {
        return null;
    }
}
