package com.example.capacidad.infra.output.r2dbc.adapter;

import com.example.capacidad.domain.model.ListCapabilityModel;
import com.example.capacidad.domain.ports.out.IListCapabilityRepositoryPort;
import com.example.capacidad.infra.output.r2dbc.entity.R2ListCapabilityTechnologyEntity;
import com.example.capacidad.infra.output.r2dbc.mapper.IListCapabilityMapperEntity;
import com.example.capacidad.infra.output.r2dbc.repository.IR2CapabilityTechnologyRepository;
import com.example.capacidad.infra.output.r2dbc.repository.IR2ListCapabilityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor

public class ListCapabilityAdapter implements IListCapabilityRepositoryPort {

    private final IR2ListCapabilityRepository ir2ListCapabilityRepository;
    private final IR2CapabilityTechnologyRepository ir2CapabilityTechnologyRepository;
    private final IListCapabilityMapperEntity iListCapabilityMapperEntity;


    @Override
    public Flux<ListCapabilityModel> findAll(Pageable pageable) {
        return ir2ListCapabilityRepository.findAllBy(pageable)
                .flatMap(entity->
                        ir2CapabilityTechnologyRepository.findByCapabilityId(entity.getId())
                                .map(R2ListCapabilityTechnologyEntity::getTechnologyId)
                                .collectList()
                                .map(techIds->
                                    ListCapabilityModel.builder()
                                                    .id(entity.getId())
                                                    .name(entity.getName())
                                                    .description(entity.getDescription())
                                                    .technologyIds(techIds)
                                                    .build()
                                )

                );

    }
}
