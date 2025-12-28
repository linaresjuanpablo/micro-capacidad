package com.example.capacidad.infra.output.r2dbc.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Table;

@Table("capability.capability_technology")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class R2CapabilityTechnologyEntity {
    private Long capabilityId;
    private Long technologyId;
}
