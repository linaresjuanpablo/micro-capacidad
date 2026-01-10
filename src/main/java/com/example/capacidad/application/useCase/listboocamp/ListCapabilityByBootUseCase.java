package com.example.capacidad.application.useCase.listboocamp;

import com.example.capacidad.domain.model.CapabilityModel;
import com.example.capacidad.domain.model.listbootcamp.CapabilityByBootModel;
import com.example.capacidad.domain.ports.in.listbootcamp.IListCapabilityByBootUseCase;
import com.example.capacidad.domain.ports.out.listbootcamp.IListCapabilityRepositoryByBootPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.List;

@Component
@RequiredArgsConstructor

public class ListCapabilityByBootUseCase implements IListCapabilityByBootUseCase {

    private final IListCapabilityRepositoryByBootPort iListCapabilityRepositoryByBootPort;

    @Override
    public Flux<CapabilityByBootModel> findByCapabilityIds(List<Long> capabilityIds) {
        return iListCapabilityRepositoryByBootPort.findByCapabilityId(capabilityIds);
    }
}
