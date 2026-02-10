package com.example.capacidad.infra.output.r2dbc.repository.deletebootcamp;

import com.example.capacidad.infra.output.r2dbc.entity.deletebootcamp.BootcampCapabilityEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.util.Objects;

public interface IR2CapabilityCountRepository extends ReactiveCrudRepository<BootcampCapabilityEntity, Long> {

    @Query("SELECT COUNT(*) FROM bootcamp_capability WHERE capability_id = :capabilityId")
    Mono<Long> countBootcampsByCapability(Long capabilityId);
}
