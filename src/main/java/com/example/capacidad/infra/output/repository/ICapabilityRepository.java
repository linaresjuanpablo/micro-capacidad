package com.example.capacidad.infra.output.repository;

import com.example.capacidad.infra.output.entity.CapabilityEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ICapabilityRepository extends ReactiveCrudRepository<CapabilityEntity, Long> {
}
