package com.example.capacidad.infra.output.r2dbc.repository.deletebootcamp;

import com.example.capacidad.infra.output.r2dbc.entity.deletebootcamp.BootcampTechnologyEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.util.Objects;

public interface IR2TechnologyCountRepository extends ReactiveCrudRepository<BootcampTechnologyEntity, Long> {

    @Query("SELECT COUNT(*) FROM bootcamp_technology WHERE technology_id = :technologyId")
    Mono<Long> countBootcampsByTechnology(Long technologyId);

}
