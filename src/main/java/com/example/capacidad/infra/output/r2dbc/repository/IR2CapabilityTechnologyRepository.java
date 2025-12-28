package com.example.capacidad.infra.output.r2dbc.repository;

import com.example.capacidad.infra.output.r2dbc.entity.R2ListCapabilityTechnologyEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface IR2CapabilityTechnologyRepository extends ReactiveCrudRepository<R2ListCapabilityTechnologyEntity, Long> {

    Flux<R2ListCapabilityTechnologyEntity> findByCapabilityId(Long capabilityId);
}
