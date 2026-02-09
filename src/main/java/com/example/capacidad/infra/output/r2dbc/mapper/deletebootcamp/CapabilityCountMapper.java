package com.example.capacidad.infra.output.r2dbc.mapper.deletebootcamp;

import com.example.capacidad.domain.model.deletebootcamp.CapabilityCountBootcampModel;
import com.example.capacidad.infra.input.dto.deletebootcamp.CapabilityBootcampCountResponse;
import com.example.capacidad.infra.output.r2dbc.entity.deletebootcamp.R2CapabilityCountBootcampEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CapabilityCountMapper {

    CapabilityBootcampCountResponse toBootcampCountResponse(Long capabilityId, Long bootcampCount);

    CapabilityCountBootcampModel toDomainModel(R2CapabilityCountBootcampEntity r2CapabilityCountBootcampEntity);

    R2CapabilityCountBootcampEntity toEntity(CapabilityCountBootcampModel capabilityCountBootcampModel);

}
