package com.example.capacidad.domain.ports.in.listbootcamp;

import com.example.capacidad.domain.model.CapabilityModel;
import com.example.capacidad.domain.model.listbootcamp.CapabilityByBootModel;
import reactor.core.publisher.Flux;

import java.util.List;

public interface IListCapabilityByBootUseCase {

    Flux<CapabilityByBootModel> findByCapabilityIds(List<Long> capabilityIds);
}
