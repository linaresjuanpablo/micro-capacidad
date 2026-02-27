package com.example.capacidad.application.usecase;


import com.example.capacidad.domain.model.ListCapabilityModel;
import com.example.capacidad.domain.model.SortBy;
import com.example.capacidad.domain.ports.out.IListCapabilityRepositoryPort;
import com.example.capacidad.domain.ports.out.IListTecnologiaClientPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;


import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class ListCapabilityUseCaseTest {
    @Mock
    private IListCapabilityRepositoryPort capabilityRepository;
    @Mock
    private IListTecnologiaClientPort tecnologiaClientPort;
    @InjectMocks
    private ListCapabilityUseCase listCapabilityUseCase;
    private ListCapabilityModel capability1;
    private ListCapabilityModel capability2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        capability1 = new ListCapabilityModel();
        capability1.setName("Backend");
        capability1.setTechnologyIds(Arrays.asList(1L, 2L));

        capability2 = new ListCapabilityModel();
        capability2.setName("Frontend");
        capability2.setTechnologyIds(Arrays.asList(3L));
    }

    @Test
    void shouldListCapabilitiesSortedByName() {
        when(capabilityRepository.findAll(any(Pageable.class)))
                .thenReturn(Flux.just(capability2, capability1));

        when(tecnologiaClientPort.findByIds(anyList()))
                .thenAnswer(invocation -> {
                    List<Long> ids = invocation.getArgument(0);
                    return Flux.fromIterable(ids).map(id -> "Tech-" + id);
                });

        StepVerifier.create(listCapabilityUseCase.list(Pageable.unpaged(), SortBy.NAME))
                .expectNextMatches(c -> c.getName().equals("Backend"))
                .expectNextMatches(c -> c.getName().equals("Frontend"))
                .verifyComplete();
    }

    @Test
    void shouldListCapabilitiesSortedByTechnologyCount() {
        when(capabilityRepository.findAll(any(Pageable.class)))
                .thenReturn(Flux.just(capability1, capability2));

        when(tecnologiaClientPort.findByIds(anyList()))
                .thenAnswer(invocation -> {
                    List<Long> ids = invocation.getArgument(0);
                    return Flux.fromIterable(ids).map(id -> "Tech-" + id);
                });

        StepVerifier.create(listCapabilityUseCase.list(Pageable.unpaged(), SortBy.TECHNOLOGY_COUNT))
                .expectNextMatches(c -> c.getName().equals("Frontend")) // 1 tecnología
                .expectNextMatches(c -> c.getName().equals("Backend"))  // 2 tecnologías
                .verifyComplete();
    }

    @Test
    void shouldHandleCapabilitiesWithoutTechnologies() {
        ListCapabilityModel capabilityNoTech = new ListCapabilityModel();
        capabilityNoTech.setName("Empty");
        capabilityNoTech.setTechnologyIds(List.of());

        when(capabilityRepository.findAll(any(Pageable.class)))
                .thenReturn(Flux.just(capabilityNoTech));

        StepVerifier.create(listCapabilityUseCase.list(Pageable.unpaged(), SortBy.NAME))
                .expectNextMatches(c -> c.getName().equals("Empty") && c.getTechnologies() == null)
                .verifyComplete();
    }


}
