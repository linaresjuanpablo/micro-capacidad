package com.example.capacidad.infra.input.controller.deletecapa;

import com.example.capacidad.application.useCase.deletecapa.DeleteCapabilityUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/capability")
@RequiredArgsConstructor

public class DeleteCapabilityController {

    private final DeleteCapabilityUseCase deleteCapabilityUseCase;

    private ResponseEntity<Void> notFound() {
        return ResponseEntity.status(404).build();
    }


    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<?>> deleteCapability(@PathVariable Long id){
        return deleteCapabilityUseCase.deleteCapability(id)
                .then(Mono.just(ResponseEntity.noContent().build()));

    }
}
