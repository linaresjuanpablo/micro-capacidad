package com.example.capacidad.domain.ports.in.deletebootcamp;

import reactor.core.publisher.Mono;

public interface IDeleteBootcamUseCase {
    Mono<Void> deleteBootcamp(Long bootcampId);
}
