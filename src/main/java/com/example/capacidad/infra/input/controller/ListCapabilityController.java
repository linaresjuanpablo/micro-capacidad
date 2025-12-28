package com.example.capacidad.infra.input.controller;

import com.example.capacidad.domain.model.ListCapabilityModel;
import com.example.capacidad.domain.model.SortBy;
import com.example.capacidad.domain.ports.in.IListCapabilityUseCase;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import org.springframework.data.domain.Pageable;


@RestController
@RequestMapping("/api/capability")

public class ListCapabilityController {
    private final IListCapabilityUseCase iListCapabilityUseCase;

    public ListCapabilityController(IListCapabilityUseCase iListCapabilityUseCase){
        this.iListCapabilityUseCase = iListCapabilityUseCase;
    }

    @GetMapping("/listar")
    public Flux<ListCapabilityModel> list (
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam SortBy sortBy
            ){
        Pageable pageable = PageRequest.of(page, size);
        return iListCapabilityUseCase.list(pageable, sortBy);
    }


}
