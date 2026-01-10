package com.example.capacidad.infra.output.r2dbc.repository.listbootcamp;

import com.example.capacidad.infra.output.r2dbc.entity.listbootcamp.R2ListCapabilityByBootEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.util.List;

public interface IR2ListCapabilityByBootRepository extends ReactiveCrudRepository<R2ListCapabilityByBootEntity, Long> {

    @Query("""
        SELECT c.id   AS capability_id,
               c.name AS capability_name,
               ct.technology_id
        FROM capability c
        JOIN capability_technology ct ON ct.capability_id = c.id
        WHERE c.id IN (:capabilityIds)
    """)
    Flux<R2ListCapabilityByBootEntity> findByCapabilityIds(List<Long> capabilityIds);
}
