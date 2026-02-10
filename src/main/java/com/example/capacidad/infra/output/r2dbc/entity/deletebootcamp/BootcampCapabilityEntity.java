package com.example.capacidad.infra.output.r2dbc.entity.deletebootcamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("bootcamp_capability")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class BootcampCapabilityEntity {

    @Id
    private Long id;

    private Long bootcamId;
    private Long capabilityId;
}
