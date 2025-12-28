package com.example.capacidad.infra.output.r2dbc.entity;

import com.example.capacidad.domain.model.TecnologiaSummary;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;
import java.util.Set;

@Data
@Table("capability")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class R2ListCapabilityEntity {

    @Id
    @Column("id")
    private Long id;

    @Column("name")
    private String name;

    @Column("description")
    private String description;

    //private List<Long> technologyIds;
    //private List<TecnologiaSummary> technologies;
    @Transient
    @MappedCollection(idColumn = "capability_id")
    private Set<R2ListCapabilityTechnologyEntity> technologies;
}
