package com.example.capacidad.application.useCase.deletebootcamp;

import com.example.capacidad.domain.ports.in.deletebootcamp.ICountBootcampsByTechnologyUseCase;
import com.example.capacidad.domain.ports.out.deletebootcamp.ITechnologyCountRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor

public class CountBootcampsByTechnologyUseCase implements ICountBootcampsByTechnologyUseCase {

    private final ITechnologyCountRepositoryPort technologyCountRepositoryPort;

    @Override
    public Mono<Long> execute(Long technologyId) {
        return technologyCountRepositoryPort.countBootcampsByTechnology(technologyId);
    }
}
