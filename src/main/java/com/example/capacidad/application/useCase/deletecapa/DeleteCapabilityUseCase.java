package com.example.capacidad.application.useCase.deletecapa;

import com.example.capacidad.domain.ports.out.deletecapa.IDeleteCapabilityRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class DeleteCapabilityUseCase {

    private final IDeleteCapabilityRepositoryPort repositoryPort;

    public Mono<Void> deleteCapability(Long capabilityId){
        return repositoryPort.existsById(capabilityId)
                .flatMap(exists ->{
                    if (!exists){
                    return Mono.error(new RuntimeException("Capability not found: " + capabilityId));

                    }
                    return repositoryPort.deleteTechnologiesByCapabilityId(capabilityId)
                            .then(repositoryPort.deleteCapabilityById(capabilityId));
                });
    }


}
