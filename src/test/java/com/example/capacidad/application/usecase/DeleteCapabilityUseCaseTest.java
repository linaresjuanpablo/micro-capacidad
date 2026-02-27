package com.example.capacidad.application.usecase;

import com.example.capacidad.application.useCase.deletecapa.DeleteCapabilityUseCase;
import com.example.capacidad.domain.ports.out.deletecapa.IDeleteCapabilityRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;

public class DeleteCapabilityUseCaseTest {

    @Mock
    private IDeleteCapabilityRepositoryPort repositoryPort;

    @InjectMocks
    private DeleteCapabilityUseCase deleteCapabilityUseCase;

    private final Long capabilityId = 1L;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldDeleteCapabilitySuccessfully() {
        when(repositoryPort.existsById(capabilityId)).thenReturn(Mono.just(true));
        when(repositoryPort.deleteTechnologiesByCapabilityId(capabilityId)).thenReturn(Mono.empty());
        when(repositoryPort.deleteCapabilityById(capabilityId)).thenReturn(Mono.empty());

        StepVerifier.create(deleteCapabilityUseCase.deleteCapability(capabilityId))
                .verifyComplete();

        verify(repositoryPort).existsById(capabilityId);
        verify(repositoryPort).deleteTechnologiesByCapabilityId(capabilityId);
        verify(repositoryPort).deleteCapabilityById(capabilityId);
    }

    @Test
    void shouldFailWhenCapabilityDoesNotExist() {
        when(repositoryPort.existsById(capabilityId)).thenReturn(Mono.just(false));

        StepVerifier.create(deleteCapabilityUseCase.deleteCapability(capabilityId))
                .expectErrorMatches(e -> e instanceof RuntimeException &&
                        e.getMessage().contains("Capability not found"))
                .verify();

        verify(repositoryPort).existsById(capabilityId);
        verify(repositoryPort, never()).deleteTechnologiesByCapabilityId(capabilityId);
        verify(repositoryPort, never()).deleteCapabilityById(capabilityId);
    }

    @Test
    void shouldPropagateErrorFromDeleteTechnologies() {
        when(repositoryPort.existsById(capabilityId)).thenReturn(Mono.just(true));
        when(repositoryPort.deleteTechnologiesByCapabilityId(capabilityId))
                .thenReturn(Mono.error(new RuntimeException("Error deleting technologies")));

        StepVerifier.create(deleteCapabilityUseCase.deleteCapability(capabilityId))
                .expectError(NullPointerException.class)
                .verify();
    }

    @Test
    void shouldPropagateErrorFromDeleteCapability() {
        when(repositoryPort.existsById(capabilityId)).thenReturn(Mono.just(true));
        when(repositoryPort.deleteTechnologiesByCapabilityId(capabilityId)).thenReturn(Mono.empty());
        when(repositoryPort.deleteCapabilityById(capabilityId))
                .thenReturn(Mono.error(new RuntimeException("Error deleting capability")));

        StepVerifier.create(deleteCapabilityUseCase.deleteCapability(capabilityId))
                .expectErrorMatches(e -> e instanceof RuntimeException &&
                        e.getMessage().contains("Error deleting capability"))
                .verify();
    }



}
