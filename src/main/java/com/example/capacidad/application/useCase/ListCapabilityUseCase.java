package com.example.capacidad.application.useCase;


import com.example.capacidad.domain.model.ListCapabilityModel;
import com.example.capacidad.domain.model.SortBy;
import com.example.capacidad.domain.ports.in.IListCapabilityUseCase;
import com.example.capacidad.domain.ports.out.IListCapabilityRepositoryPort;
import com.example.capacidad.domain.ports.out.IListTecnologiaClientPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.data.domain.Pageable;

import java.util.Comparator;


@Component
@RequiredArgsConstructor
@Slf4j

public class ListCapabilityUseCase implements IListCapabilityUseCase {

    private final IListCapabilityRepositoryPort iListCapabilityRepositoryPort;
    private final IListTecnologiaClientPort iListTecnologiaClientPort;

    @Override
    public Flux<ListCapabilityModel> list(Pageable pageable, SortBy sortBy) {

        return iListCapabilityRepositoryPort.findAll(pageable)
                .flatMap(this::enrichWithTecnologies, 5)
                .transform(flux -> applySort(flux, sortBy));
    }

    private Mono<ListCapabilityModel> enrichWithTecnologies(ListCapabilityModel capability){
        if (capability.getTechnologyIds() == null || capability.getTechnologyIds().isEmpty()){
            return Mono.just(capability);
        }
        return iListTecnologiaClientPort.findByIds(capability.getTechnologyIds())
                .collectList()
                .map(techs ->{
                    capability.setTechnologies(techs);
                    return capability;
                });
    }

    private Flux<ListCapabilityModel> applySort(Flux<ListCapabilityModel> flux, SortBy sortBy){
        return switch (sortBy){
            case NAME -> flux.sort(Comparator.comparing(ListCapabilityModel::getName));
            case TECHNOLOGY_COUNT -> flux.sort(Comparator.comparing(c -> c.getTechnologies() == null ? 0 :
                c.getTechnologies().size()));
        };
    }
}














