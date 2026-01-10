package com.example.capacidad.domain.ports.out.listbootcamp;


import com.example.capacidad.domain.model.listbootcamp.TecnologiaByBootSummary;
import reactor.core.publisher.Flux;

import java.util.List;

public interface ITechnologyCapaClientPort {

    Flux<TecnologiaByBootSummary> findByIds(List<Long> technologyIds);
}
