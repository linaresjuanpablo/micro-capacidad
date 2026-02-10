package com.example.capacidad.infra.output.r2dbc.mapper.listbootcamp;

import com.example.capacidad.domain.model.listbootcamp.CapabilityByBootModel;
import com.example.capacidad.domain.model.listbootcamp.TecnologiaByBootSummary;
import com.example.capacidad.infra.output.r2dbc.entity.listbootcamp.R2ListCapabilityByBootEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class ListCapabilityMapperByBootEntity {

    public List<CapabilityByBootModel> toModel(
            List<R2ListCapabilityByBootEntity> rows,
            List<TecnologiaByBootSummary> technologies
    ) {

        Map<Long, TecnologiaByBootSummary> techMap =
                technologies.stream()
                        .collect(Collectors.toMap(
                                TecnologiaByBootSummary::getId,
                                t -> t
                        ));

        Map<Long, List<R2ListCapabilityByBootEntity>> grouped =
                rows.stream()
                        .collect(Collectors.groupingBy(
                                R2ListCapabilityByBootEntity::getCapabilityId
                        ));

        return grouped.values().stream()
                .map(capRows -> {
                    R2ListCapabilityByBootEntity first = capRows.get(0);

                    return CapabilityByBootModel.builder()
                            .id(first.getCapabilityId())
                            .name(first.getCapabilityName())
                            .technologies(
                                    capRows.stream()
                                            .map(r -> techMap.get(r.getTechnologyId()))
                                            .filter(Objects::nonNull)
                                            .toList()
                            )
                            .build();
                })
                .toList();
    }

}
