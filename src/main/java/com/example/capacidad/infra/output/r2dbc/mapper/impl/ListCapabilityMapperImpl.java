package com.example.capacidad.infra.output.r2dbc.mapper.impl;

import com.example.capacidad.domain.model.ListCapabilityModel;
import com.example.capacidad.infra.output.r2dbc.entity.R2ListCapabilityEntity;
import com.example.capacidad.infra.output.r2dbc.entity.R2ListCapabilityTechnologyEntity;
import com.example.capacidad.infra.output.r2dbc.mapper.IListCapabilityMapperEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component

public class ListCapabilityMapperImpl implements IListCapabilityMapperEntity {

    @Override
    public ListCapabilityModel toListModel(R2ListCapabilityEntity r2ListCapabilityEntity) {

        List<Long> techIds = new ArrayList<>();

        if (r2ListCapabilityEntity.getTechnologies() != null){
            r2ListCapabilityEntity.getTechnologies()
                    .forEach(t-> techIds.add(t.getTechnologyId())
            );
        }

        return ListCapabilityModel.builder()
                .id(r2ListCapabilityEntity.getId())
                .name(r2ListCapabilityEntity.getName())
                .description(r2ListCapabilityEntity.getDescription())
                .technologyIds(techIds)
                .technologyIds(new ArrayList<>())
                .build();
    }
}
