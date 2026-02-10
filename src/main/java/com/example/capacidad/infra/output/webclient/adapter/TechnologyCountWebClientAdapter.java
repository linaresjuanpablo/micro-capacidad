package com.example.capacidad.infra.output.webclient.adapter;

import com.example.capacidad.domain.ports.out.deletebootcamp.ITechnologyCountRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor

public class TechnologyCountWebClientAdapter implements ITechnologyCountRepositoryPort {

    private final @Qualifier("bootcampWebClient") WebClient bootcampWebClient;

    @Override
    public Mono<Long> countBootcampsByTechnology(Long technologyId) {
        return bootcampWebClient
                .get()
                .uri("/api/technologies/{id}/bootcamps/count", technologyId)
                .retrieve()
                .bodyToMono(Long.class);
    }
}
