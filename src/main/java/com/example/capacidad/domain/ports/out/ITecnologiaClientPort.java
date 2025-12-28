package com.example.capacidad.domain.ports.out;

import com.example.capacidad.domain.model.TecnologiaSummary;
import com.example.capacidad.infra.input.controller.TechnologyDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ITecnologiaClientPort {

    Mono<Boolean> existsById(Long tecnologiaId);

   // Mono<List<TechnologyDto>> getTechnologiesByIds(List<Long> ids);
    Flux<TecnologiaSummary> findByIds(List<Long> ids);
}
