package com.example.capacidad.infra.output.r2dbc.repository;

import com.example.capacidad.infra.output.r2dbc.entity.R2ListCapabilityEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface IR2ListCapabilityRepository extends ReactiveCrudRepository<R2ListCapabilityEntity, Long> {

    Flux<R2ListCapabilityEntity> findAllBy(Pageable pageable);
}
