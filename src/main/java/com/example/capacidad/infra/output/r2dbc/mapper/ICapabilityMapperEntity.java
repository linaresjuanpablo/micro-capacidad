package com.example.capacidad.infra.output.r2dbc.mapper;

import com.example.capacidad.domain.model.CapabilityModel;
import com.example.capacidad.infra.output.r2dbc.entity.R2CapabilityEntity;
import org.mapstruct.Mapper;

import java.util.List;

//@Mapper(componentModel = "spring")

public interface ICapabilityMapperEntity {

    R2CapabilityEntity r2Entity(CapabilityModel capabilityModel);

    CapabilityModel capaModel(R2CapabilityEntity r2CapabilityEntity, List<Long> techIds);


}
