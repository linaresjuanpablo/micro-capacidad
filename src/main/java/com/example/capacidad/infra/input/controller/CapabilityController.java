package com.example.capacidad.infra.input.controller;

import com.example.capacidad.domain.model.CapabilityModel;
import com.example.capacidad.domain.ports.in.ICreateCapabilityUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/capability")

public class CapabilityController {

    private final ICreateCapabilityUseCase iCreateCapabilityUseCase;

    public CapabilityController(ICreateCapabilityUseCase iCreateCapabilityUseCase){
        this.iCreateCapabilityUseCase = iCreateCapabilityUseCase;
    }

    @PostMapping("/create")
    public Mono<ResponseEntity<CapabilityModel>> createCapability(@RequestBody CapabilityModel capabilityModel){
        return iCreateCapabilityUseCase.createCapa(capabilityModel)
                .map(createCapability-> new ResponseEntity<>(createCapability, HttpStatus.CREATED));
    }



}
