package com.example.capacidad.domain.ports.out.deletebootcamp;

import reactor.core.publisher.Mono;

public interface ITechnologyCountRepositoryPort {

    Mono<Long> countBootcampsByTechnology(Long technologyId);
}
