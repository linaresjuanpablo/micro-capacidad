package com.example.capacidad.domain.ports.in;

import com.example.capacidad.domain.model.CapabilityModel;
import reactor.core.publisher.Mono;

public interface ICreateCapabilityUseCase {

    Mono<CapabilityModel> createCapa(CapabilityModel capabilityModel);
}
