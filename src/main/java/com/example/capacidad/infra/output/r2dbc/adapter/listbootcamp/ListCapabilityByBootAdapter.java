package com.example.capacidad.infra.output.r2dbc.adapter.listbootcamp;


import com.example.capacidad.domain.model.listbootcamp.CapabilityByBootModel;
import com.example.capacidad.domain.ports.out.listbootcamp.IListCapabilityRepositoryByBootPort;
import com.example.capacidad.domain.ports.out.listbootcamp.ITechnologyCapaClientPort;
import com.example.capacidad.infra.output.r2dbc.entity.listbootcamp.R2ListCapabilityByBootEntity;
import com.example.capacidad.infra.output.r2dbc.mapper.listbootcamp.ListCapabilityMapperByBootEntity;
import com.example.capacidad.infra.output.r2dbc.repository.listbootcamp.IR2ListCapabilityByBootRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ListCapabilityByBootAdapter implements IListCapabilityRepositoryByBootPort {

    private final IR2ListCapabilityByBootRepository ir2ListCapabilityByBootRepository;
    private final ListCapabilityMapperByBootEntity mapper;
    private final ITechnologyCapaClientPort iTechnologyCapaClientPort;

    @Override
    public Flux<CapabilityByBootModel> findByCapabilityId(List<Long> capabilityIds) {
        return ir2ListCapabilityByBootRepository
                .findByCapabilityIds(capabilityIds)
                .collectList()
                .flatMapMany(rows->{
                    List<Long> technologyIds = rows.stream()
                            .map(r-> r.getTechnologyId())
                            .distinct()
                            .toList();

                    return iTechnologyCapaClientPort.findByIds(technologyIds)
                            .collectList()
                            .map(techs-> mapper.toModel(rows, techs))
                            .flatMapMany(Flux::fromIterable);
                });



    }
}
