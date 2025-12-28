package com.example.capacidad.infra.output.r2dbc.mapper;

import com.example.capacidad.domain.model.ListCapabilityModel;
import com.example.capacidad.infra.output.r2dbc.entity.R2ListCapabilityEntity;

public interface IListCapabilityMapperEntity {

    ListCapabilityModel toListModel(R2ListCapabilityEntity r2ListCapabilityEntity);
}
