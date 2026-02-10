package com.example.capacidad.domain.ports.out.deletecapa;

import reactor.core.publisher.Mono;

public interface IDeleteCapabilityRepositoryPort {
    Mono<Void> deleteCapabilityById(Long capabilityId);
    Mono<Void> deleteTechnologiesByCapabilityId(Long capabilityId);
    Mono<Boolean> existsById(Long capabilityId);
}
