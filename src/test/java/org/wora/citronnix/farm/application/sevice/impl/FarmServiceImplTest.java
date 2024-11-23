package org.wora.citronnix.farm.application.sevice.impl;

import static org.junit.jupiter.api.Assertions.*;

import lombok.Builder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.domain.Specification;
import org.wora.citronnix.farm.application.Mapper.FarmMapper;
import org.wora.citronnix.farm.application.dto.request.FarmRequestDTO;
import org.wora.citronnix.farm.application.dto.request.FarmSearchCriteria;
import org.wora.citronnix.farm.application.dto.response.FarmResponseDTO;
import org.wora.citronnix.farm.domain.entity.Farm;
import org.wora.citronnix.farm.domain.repository.FarmRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

class FarmServiceImplTest {

    @Mock
    private FarmRepository farmRepository;

    @Mock
    private FarmMapper farmMapper;

    @InjectMocks
    private FarmServiceImpl farmService;

    private Farm testFarm;
    private FarmRequestDTO testFarmRequestDTO;
    private FarmResponseDTO testFarmResponseDTO;

    @BeforeEach
    void setUp() {
        testFarm = Farm.builder()
                .id(1L)
                .nom("Test Farm")
                .localisation("Test Location")
                .superficie(100.0)
                .dateCreation(LocalDate.now())
                .build();

        testFarmRequestDTO = new FarmRequestDTO(
                "Test Farm",
                "Test Location",
                100.0,
                LocalDate.now()
        );

        testFarmResponseDTO = new FarmResponseDTO(
                1L,
                "Test Farm",
                "Test Location",
                100.0,
                LocalDate.now(),
                List.of()
        );
    }

    @Nested
    @DisplayName("Find By ID Tests")
    class FindByIdTests {

        @Test
        @DisplayName("Should return farm when exists")
        void shouldReturnFarmWhenExists() {
            when(farmRepository.findById(1L)).thenReturn(Optional.of(testFarm));
            when(farmMapper.toFarmResponseDto(testFarm)).thenReturn(testFarmResponseDTO);

            FarmResponseDTO result = farmService.findById(1L);

            assertNotNull(result);
            assertEquals(testFarmResponseDTO.id(), result.id());
            verify(farmRepository).findById(1L);
        }

        @Test
        @DisplayName("Should throw EntityNotFoundException when farm doesn't exist")
        void shouldThrowExceptionWhenFarmNotFound() {
            when(farmRepository.findById(1L)).thenReturn(Optional.empty());

            assertThrows(EntityNotFoundException.class, () -> farmService.findById(1L));
            verify(farmRepository).findById(1L);
        }
    }

}