package com.example.capacidad.infra.output.r2dbc.repository;

import com.example.capacidad.infra.output.r2dbc.entity.R2CapabilityTechnologyEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface ICapabilityTechnologyRepository extends ReactiveCrudRepository<R2CapabilityTechnologyEntity, Long> {
    Flux<R2CapabilityTechnologyEntity> findByCapabilityId(Long capabilityId);
}


