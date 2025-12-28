package com.example.capacidad.domain.ports.out;

import com.example.capacidad.domain.model.TecnologiaSummary;
import reactor.core.publisher.Flux;

import java.util.List;

public interface IListTecnologiaClientPort {
    Flux<TecnologiaSummary> findByIds(List<Long> ids);
}
