package com.example.capacidad.infra.output.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Table;

@Table("capability.capability_technology")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class CapabilityTechnologyEntity {
    private Long capabilityId;
    private Long technologyId;

}
