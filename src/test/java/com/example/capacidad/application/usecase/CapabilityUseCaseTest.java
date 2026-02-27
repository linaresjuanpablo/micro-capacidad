package com.example.capacidad.application.usecase;

import com.example.capacidad.application.useCase.CapabilityUseCase;
import com.example.capacidad.domain.exception.DataAccessException;
import com.example.capacidad.domain.exception.DuplicateResourceException;
import com.example.capacidad.domain.exception.ValidationException;
import com.example.capacidad.domain.model.CapabilityModel;
import com.example.capacidad.domain.ports.out.ICapabilityRepositoryPor;
import com.example.capacidad.domain.ports.out.ITecnologiaClientPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;


public class CapabilityUseCaseTest {

    @Mock
    private ICapabilityRepositoryPor capabilityRepository;
    @Mock
    private ITecnologiaClientPort tecnologiaClientPort;
    @InjectMocks
    private CapabilityUseCase capabilityUseCase;
    private CapabilityModel validModel;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        validModel = new CapabilityModel();
        validModel.setName("capacidad");
        validModel.setDescription("descripcion");
        validModel.setTechnologyIds(Arrays.asList(1L, 2L, 3L));
    }

    @Test
    void shouldCreateCapabilitySuccessfully() {
        when(capabilityRepository.existsByName("capacidad")).thenReturn(Mono.just(false));
        when(tecnologiaClientPort.existsById(anyLong())).thenReturn(Mono.just(true));
        when(capabilityRepository.save(any())).thenReturn(Mono.just(validModel));

        StepVerifier.create(capabilityUseCase.createCapa(validModel))
                .expectNextMatches(c -> c.getName().equals("CAPACIDAD"))
                .verifyComplete();
    }

    @Test
    void shouldFailWhenNameIsInvalid() {
        validModel.setName(" ");
        StepVerifier.create(capabilityUseCase.createCapa(validModel))
                .expectErrorMatches(e -> e instanceof ValidationException &&
                        ((ValidationException) e).getCode().equals("ERR_NAME_INVALID"))
                .verify();
    }

    @Test
    void shouldFailWhenDescriptionIsInvalid() {
        validModel.setDescription("");
        StepVerifier.create(capabilityUseCase.createCapa(validModel))
                .expectErrorMatches(e -> e instanceof ValidationException &&
                        ((ValidationException) e).getCode().equals("ERR_DESCRI_INVALID"))
                .verify();
    }

    @Test
    void shouldFailWhenTechnologiesAreLessThanMinimum() {
        validModel.setTechnologyIds(List.of(1L, 2L));
        StepVerifier.create(capabilityUseCase.createCapa(validModel))
                .expectErrorMatches(e -> e instanceof ValidationException &&
                        ((ValidationException) e).getCode().equals("ERR_TECNOLOGIA_MINIMO"))
                .verify();
    }

    @Test
    void shouldFailWhenTechnologiesExceedMaximum() {
        validModel.setTechnologyIds(Arrays.asList(new Long[25])); // más de 20
        StepVerifier.create(capabilityUseCase.createCapa(validModel))
                .expectErrorMatches(e -> e instanceof ValidationException &&
                        ((ValidationException) e).getCode().equals("ERR_TECNOLOGIA_MAXIMO"))
                .verify();
    }

    @Test
    void shouldFailWhenTechnologiesAreDuplicated() {
        validModel.setTechnologyIds(Arrays.asList(1L, 1L, 2L, 3L));
        StepVerifier.create(capabilityUseCase.createCapa(validModel))
                .expectErrorMatches(e -> e instanceof ValidationException &&
                        ((ValidationException) e).getCode().equals("ERR_DUPLICATE"))
                .verify();
    }

    @Test
    void shouldFailWhenCapabilityAlreadyExists() {
        when(capabilityRepository.existsByName("capacidad")).thenReturn(Mono.just(true));

        StepVerifier.create(capabilityUseCase.createCapa(validModel))
                .expectErrorMatches(e -> e instanceof DuplicateResourceException &&
                        ((DuplicateResourceException) e).getCode().equals("ERR_CAPABILITY_EXISTS"))
                .verify();
    }

    @Test
    void shouldFailWhenTechnologyNotFound() {
        when(capabilityRepository.existsByName("capacidad")).thenReturn(Mono.just(false));
        when(tecnologiaClientPort.existsById(anyLong())).thenReturn(Mono.just(false));

        StepVerifier.create(capabilityUseCase.createCapa(validModel))
                .expectErrorMatches(e -> e instanceof ValidationException &&
                        ((ValidationException) e).getCode().equals("ERR_TECNOLOGIA_NOT_FOUND"))
                .verify();
    }

    @Test
    void shouldWrapUnexpectedErrorInDataAccessException() {
        when(capabilityRepository.existsByName("capacidad")).thenReturn(Mono.just(false));
        when(tecnologiaClientPort.existsById(anyLong())).thenReturn(Mono.just(true));
        when(capabilityRepository.save(any())).thenReturn(Mono.error(new RuntimeException("DB error")));

        StepVerifier.create(capabilityUseCase.createCapa(validModel))
                .expectErrorMatches(e -> e instanceof DataAccessException &&
                        ((DataAccessException) e).getCode().equals("ERR_CAPABILITY_EXISTS"))
                .verify();
    }



}
