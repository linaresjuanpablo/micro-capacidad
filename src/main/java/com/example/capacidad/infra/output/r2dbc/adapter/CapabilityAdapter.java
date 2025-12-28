package com.example.capacidad.infra.output.r2dbc.adapter;

import com.example.capacidad.domain.model.CapabilityModel;
import com.example.capacidad.domain.ports.out.ICapabilityRepositoryPor;
import com.example.capacidad.infra.output.r2dbc.entity.R2CapabilityEntity;
import com.example.capacidad.infra.output.r2dbc.entity.R2CapabilityTechnologyEntity;
import com.example.capacidad.infra.output.r2dbc.mapper.ICapabilityMapperEntity;
import com.example.capacidad.infra.output.r2dbc.repository.ICapabilityTechnologyRepository;
import com.example.capacidad.infra.output.r2dbc.repository.IR2CapabilityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.awt.print.Pageable;

@Component
@RequiredArgsConstructor

public class CapabilityAdapter implements ICapabilityRepositoryPor {

    private final IR2CapabilityRepository ir2CapabilityRepository;
    private final ICapabilityMapperEntity iCapabilityMapperEntity;
    private final ICapabilityTechnologyRepository iCapabilityTechnologyRepository;

    @Override
    public Mono<CapabilityModel> save(CapabilityModel capabilityModel) {
        R2CapabilityEntity entity = iCapabilityMapperEntity.r2Entity(capabilityModel);

        return ir2CapabilityRepository.save(entity)
                .flatMap(saved -> {
                    // insertar relaciones en la tabla puente
                    return Flux.fromIterable(capabilityModel.getTechnologyIds())
                            .flatMap(techId -> {
                                R2CapabilityTechnologyEntity rel = new R2CapabilityTechnologyEntity();
                                rel.setCapabilityId(saved.getId());
                                rel.setTechnologyId(techId);
                                return iCapabilityTechnologyRepository.save(rel);
                            })
                            .then(Mono.just(iCapabilityMapperEntity.capaModel(saved, capabilityModel.getTechnologyIds())));
                });
    }

    @Override
    public Mono<CapabilityModel> findById(Long id) {
        return null;
    }

    @Override
    public Mono<Boolean> existsByName(String name) {

        return ir2CapabilityRepository.existsByName(name);
    }

    /*@Override
    public Flux<CapabilityModel> findAll(Pageable pageable) {
        return ir2CapabilityRepository.findAllBy(pageable)
                .map(iCapabilityMapperEntity::capaModel);
    }*/
}
