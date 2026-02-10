package com.example.capacidad.infra.output.r2dbc.adapter.deletecapa;

import com.example.capacidad.domain.ports.out.deletecapa.IDeleteCapabilityRepositoryPort;
import com.example.capacidad.infra.output.r2dbc.repository.deletebootcamp.CapabilityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor

public class DeleteCapabilityRepositoryAdapter implements IDeleteCapabilityRepositoryPort {

    private final CapabilityRepository capabilityRepository;

    @Override
    public Mono<Void> deleteCapabilityById(Long capabilityId) {
        return capabilityRepository.deleteCapabilityById(capabilityId);
    }

    @Override
    public Mono<Void> deleteTechnologiesByCapabilityId(Long capabilityId) {
        return capabilityRepository.deleteTechnologiesByCapabilityId(capabilityId);
    }

    @Override
    public Mono<Boolean> existsById(Long capabilityId) {
        return capabilityRepository.existsById(capabilityId);
    }
}
