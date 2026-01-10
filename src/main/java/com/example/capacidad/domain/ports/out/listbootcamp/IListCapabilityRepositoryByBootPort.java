package com.example.capacidad.domain.ports.out.listbootcamp;

import com.example.capacidad.domain.model.CapabilityModel;
import com.example.capacidad.domain.model.listbootcamp.CapabilityByBootModel;
import reactor.core.publisher.Flux;

import java.util.List;

public interface IListCapabilityRepositoryByBootPort {

    Flux<CapabilityByBootModel> findByCapabilityId(List<Long> capabilityIds);
}
