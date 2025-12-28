package com.example.capacidad.domain.ports.out;

import com.example.capacidad.domain.model.CapabilityModel;
import com.example.capacidad.domain.model.ListCapabilityModel;
import reactor.core.publisher.Flux;
import org.springframework.data.domain.Pageable;


public interface IListCapabilityRepositoryPort {
    Flux<ListCapabilityModel> findAll(Pageable pageable);
}
