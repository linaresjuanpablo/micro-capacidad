package com.example.capacidad.application.useCase;

import com.example.capacidad.domain.exception.DataAccessException;
import com.example.capacidad.domain.exception.DuplicateResourceException;
import com.example.capacidad.domain.exception.ValidationException;

import com.example.capacidad.domain.model.CapabilityModel;
import com.example.capacidad.domain.ports.in.ICreateCapabilityUseCase;
import com.example.capacidad.domain.ports.out.ICapabilityRepositoryPor;
import com.example.capacidad.domain.ports.out.ITecnologiaClientPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
@Slf4j

public class CapabilityUseCase implements ICreateCapabilityUseCase {

    private final ICapabilityRepositoryPor iCapabilityRepositoryPor;
    private final ITecnologiaClientPort tecnologiaClientPort;

    private static final int MIN_TECH = 3;
    private static final int MAX_TECH = 20;

    private static final String ERR_NAME_INVALID = "ERR_NAME_INVALID";
    private static final String ERR_DESCRI_INVALID = "ERR_DESCRI_INVALID";
    private static final String ERR_CAPABILITY_EXISTS = "ERR_CAPABILITY_EXISTS";
    private static final String ERR_TECNOLOGIA_MINIMO = "ERR_TECNOLOGIA_MINIMO";
    private static final String ERR_TECNOLOGIA_MAXIMO = "ERR_TECNOLOGIA_MAXIMO";
    private static final String ERR_DUPLICATE = "ERR_DUPLICATE";
    private static final String ERR_TECNOLOGIA_NOT_FOUND = "ERR_TECNOLOGIA_NOT_FOUND";

    public Mono<CapabilityModel> createCapa(CapabilityModel capabilityModel) {

        return validateCapa(capabilityModel)
                .map(c -> {
                    c.setName(c.getName().toUpperCase());
                    c.setDescription(c.getDescription().toUpperCase());
                    return c;
                })
                // VALIDAR TECNOLOGÍAS REMOTAS
                .flatMap(this::validateTecnologiasExist)
                .flatMap(iCapabilityRepositoryPor::save)
                .onErrorResume(e -> {
                    if (e instanceof ValidationException || e instanceof DuplicateResourceException) {
                        return Mono.error(e);
                    }
                    log.error("Error en el proceso de guardar capacidad: {}", e.getMessage());
                    return Mono.error(new DataAccessException(
                            ERR_CAPABILITY_EXISTS,
                            "Error registrando capacidad: " + e.getMessage()
                    ));
                });
    }

    public Mono<CapabilityModel> validateCapa(CapabilityModel capabilityModel) {

        if (capabilityModel.getName() == null || capabilityModel.getName().isBlank()) {
            return Mono.error(new ValidationException(
                    ERR_NAME_INVALID, "El nombre de la capacidad es inválido"));
        }

        if (capabilityModel.getDescription() == null || capabilityModel.getDescription().isBlank()) {
            return Mono.error(new ValidationException(
                    ERR_DESCRI_INVALID, "La descripción es inválida"));
        }

        if (capabilityModel.getTechnologyIds() == null ||
                capabilityModel.getTechnologyIds().size() < MIN_TECH) {
            return Mono.error(new ValidationException(
                    ERR_TECNOLOGIA_MINIMO, "La tecnología mínima es 3"));
        }

        if (capabilityModel.getTechnologyIds().size() > MAX_TECH) {
            return Mono.error(new ValidationException(
                    ERR_TECNOLOGIA_MAXIMO, "La tecnología máxima es 20"));
        }

        Set<Long> set = new HashSet<>(capabilityModel.getTechnologyIds());
        if (set.size() != capabilityModel.getTechnologyIds().size()) {
            return Mono.error(new ValidationException(
                    ERR_DUPLICATE, "La tecnología está duplicada"));
        }

        // Validar unicidad por nombre
        return iCapabilityRepositoryPor.existsByName(capabilityModel.getName())
                .flatMap(existing -> {
                    if (existing) {
                        log.warn("Ya existe la capacidad: {}", capabilityModel.getName());
                        return Mono.<CapabilityModel>error(
                                new DuplicateResourceException(
                                        ERR_CAPABILITY_EXISTS,
                                        "Ya existe una capacidad con ese nombre"));
                    }
                    return Mono.just(capabilityModel);
                });

    }

    /**
     * 🔹 Valida que TODAS las tecnologías existan en el microservicio Tecnología
     */
    private Mono<CapabilityModel> validateTecnologiasExist(CapabilityModel model) {

        return Flux.fromIterable(model.getTechnologyIds())
                .flatMap(tecnologiaClientPort::existsById)
                .collectList()
                .flatMap(results -> {
                    if (results.contains(false)) {
                        return Mono.error(new ValidationException(
                                ERR_TECNOLOGIA_NOT_FOUND,
                                "Una o más tecnologías no existen"));
                    }
                    return Mono.just(model);
                });
    }
}
