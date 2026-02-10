package com.example.capacidad.domain.model.deletebootcamp;

import lombok.*;

import java.util.List;

@Value
@Builder

public class CapabilityCountBootcampModel {
    private Long id;
    private String name;
    private String description;

    private List<Long> bootcampIds;
    private List<Long> technologyIds;


}
