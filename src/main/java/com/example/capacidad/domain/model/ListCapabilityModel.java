package com.example.capacidad.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ListCapabilityModel {
    private Long id;
    private String name;
    private String description;
    private List<Long> technologyIds;
    private List<TecnologiaSummary> technologies;
}
