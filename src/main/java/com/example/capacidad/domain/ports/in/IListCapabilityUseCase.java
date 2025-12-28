package com.example.capacidad.domain.ports.in;

import com.example.capacidad.domain.model.ListCapabilityModel;
import com.example.capacidad.domain.model.SortBy;
import reactor.core.publisher.Flux;
import org.springframework.data.domain.Pageable;


public interface IListCapabilityUseCase {

    Flux<ListCapabilityModel> list (Pageable pageable, SortBy sortBy);
}
