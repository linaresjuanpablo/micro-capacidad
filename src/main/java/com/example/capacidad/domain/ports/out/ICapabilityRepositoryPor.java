package com.example.capacidad.domain.ports.out;

import com.example.capacidad.domain.model.CapabilityModel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.awt.print.Pageable;

public interface ICapabilityRepositoryPor {

    Mono<CapabilityModel> save(CapabilityModel capabilityModel);
    Mono<CapabilityModel> findById(Long id);
    Mono<Boolean> existsByName(String name);
    //Flux<CapabilityModel> findAll(Pageable pageable);
}
