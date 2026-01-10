package com.example.capacidad.domain.model.listbootcamp;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CapabilityByBootModel {

    private Long id;
    private String name;
    private List<TecnologiaByBootSummary> technologies;
}
