package com.example.capacidad.domain.ports.in.deletebootcamp;

import reactor.core.publisher.Mono;

public interface ICountBootcampsByTechnologyUseCase {

    Mono<Long> execute(Long technologyId);
}
