package com.example.capacidad.infra.output.r2dbc.entity.listbootcamp;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Column;

@Data
public class R2ListCapabilityByBootEntity {

    @Column("capability_id")
    private Long capabilityId;

    @Column("capability_name")
    private String capabilityName;

    @Column("technology_id")
    private Long technologyId;

   // @Column("technology_name")
    //private String technologyName;

}
