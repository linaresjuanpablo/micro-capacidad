package com.example.capacidad.infra.input.controller.listbootcamp;

import com.example.capacidad.domain.model.CapabilityModel;
import com.example.capacidad.domain.model.listbootcamp.CapabilityByBootModel;
import com.example.capacidad.domain.ports.in.IListCapabilityUseCase;
import com.example.capacidad.domain.ports.in.listbootcamp.IListCapabilityByBootUseCase;
import com.example.capacidad.infra.output.r2dbc.repository.listbootcamp.IR2ListCapabilityByBootRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("/api/capability")
@RequiredArgsConstructor

public class ListCapabilityByBootcampController {

    private final IListCapabilityByBootUseCase  iListCapabilityByBootUseCase;

    @GetMapping("/by-ids")
    public Flux<CapabilityByBootModel> findByIds(
            @RequestParam(name = "ids") List<Long> ids){
        return iListCapabilityByBootUseCase.findByCapabilityIds(ids);
    }
}
