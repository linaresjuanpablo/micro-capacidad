package com.example.capacidad.infra.output.r2dbc.repository;

import com.example.capacidad.infra.output.r2dbc.entity.R2CapabilityEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IR2CapabilityRepository extends ReactiveCrudRepository<R2CapabilityEntity, Long> {

    Flux<R2CapabilityEntity> findAll();

    Mono<Boolean> existsByName(String name);

}
