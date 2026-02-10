package com.example.capacidad.infra.output.r2dbc.repository.deletebootcamp;

import com.example.capacidad.infra.output.entity.CapabilityEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface CapabilityRepository extends ReactiveCrudRepository<CapabilityEntity, Long> {

    @Query("DELETE FROM capability_technology WHERE capability_id = :capabilityId")
    Mono<Void> deleteTechnologiesByCapabilityId(Long capabilityId);

    @Query("DELETE FROM capability WHERE id = :capabilityId")
    Mono<Void> deleteCapabilityById(Long capabilityId);

    @Query("SELECT COUNT(*) > 0 FROM capability WHERE id = :capabilityId")
    Mono<Boolean> existsById(Long capabilityId);
}
