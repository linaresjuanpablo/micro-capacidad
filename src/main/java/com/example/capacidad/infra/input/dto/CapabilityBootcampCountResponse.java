package com.example.capacidad.infra.input.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class CapabilityBootcampCountResponse {

    private Long capabilityId;
    private Long bootcampCount;

}
