package com.example.capacidad.domain.model.listbootcamp;

import com.example.capacidad.domain.model.TecnologiaSummary;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor

public class CapabilitySummary {

    private Long id;

    private String name;

    private List<TecnologiaSummary> technologies;


}
