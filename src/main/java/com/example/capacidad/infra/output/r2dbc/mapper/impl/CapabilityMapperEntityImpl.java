package com.example.capacidad.infra.output.r2dbc.mapper.impl;

import com.example.capacidad.domain.model.CapabilityModel;
import com.example.capacidad.infra.output.r2dbc.entity.R2CapabilityEntity;
import com.example.capacidad.infra.output.r2dbc.mapper.ICapabilityMapperEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component

public class CapabilityMapperEntityImpl implements ICapabilityMapperEntity {

    @Override
    public R2CapabilityEntity r2Entity(CapabilityModel capabilityModel) {
        R2CapabilityEntity entity = new R2CapabilityEntity();
        entity.setId(capabilityModel.getId());
        entity.setName(capabilityModel.getName());
        entity.setDescription(capabilityModel.getDescription());
        return entity;
    }

    @Override
    public CapabilityModel capaModel(R2CapabilityEntity r2CapabilityEntity, List<Long> techIds) {
        CapabilityModel capabilityModel = new CapabilityModel();
        capabilityModel.setId(r2CapabilityEntity.getId());
        capabilityModel.setName(r2CapabilityEntity.getName());
        capabilityModel.setDescription(r2CapabilityEntity.getDescription());
        capabilityModel.setTechnologyIds(techIds);
        return capabilityModel;
    }
}
