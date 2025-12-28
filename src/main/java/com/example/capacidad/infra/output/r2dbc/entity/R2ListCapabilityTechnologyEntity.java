package com.example.capacidad.infra.output.r2dbc.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(schema = "capability", name = "capability_technology")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class R2ListCapabilityTechnologyEntity {

    @Id
    private Long id;

    @Column("capability_id")
    private Long capabilityId;

    @Column("technology_id")
    private Long technologyId;
}
