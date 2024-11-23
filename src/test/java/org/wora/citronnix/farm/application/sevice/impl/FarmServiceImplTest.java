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
    @Nested
    @DisplayName("Save Farm Tests")
    class SaveFarmTests {

        @Test
        @DisplayName("Should save farm successfully")
        void shouldSaveFarmSuccessfully() {
            when(farmRepository.existsByNom(anyString())).thenReturn(false);
            when(farmMapper.toFarm(testFarmRequestDTO)).thenReturn(testFarm);
            when(farmRepository.save(any(Farm.class))).thenReturn(testFarm);
            when(farmMapper.toFarmResponseDto(testFarm)).thenReturn(testFarmResponseDTO);

            FarmResponseDTO result = farmService.save(testFarmRequestDTO);

            assertNotNull(result);
            assertEquals(testFarmResponseDTO.nom(), result.nom());
            verify(farmRepository).save(any(Farm.class));
        }

        @Test
        @DisplayName("Should throw EntityExistsException when farm name already exists")
        void shouldThrowExceptionWhenFarmNameExists() {
            when(farmRepository.existsByNom(anyString())).thenReturn(true);

            assertThrows(EntityExistsException.class, () -> farmService.save(testFarmRequestDTO));
            verify(farmRepository, never()).save(any(Farm.class));
        }
    }
    @Nested
    @DisplayName("Search Farms Tests")
    class SearchFarmsTests {

        @Test
        @DisplayName("Should return filtered farms")
        void shouldReturnFilteredFarms() {
            FarmSearchCriteria criteria = new FarmSearchCriteria(
                    "Test",
                    null,
                    50.0,
                    150.0,
                    LocalDate.now().minusYears(1),
                    LocalDate.now()
            );

            List<Farm> farms = Arrays.asList(testFarm);
            doReturn(farms).when(farmRepository).findAll(any(Specification.class));
            when(farmMapper.toDto(any(Farm.class))).thenReturn(testFarmResponseDTO);

            List<FarmResponseDTO> results = farmService.searchFarms(criteria);

            assertNotNull(results);
            assertFalse(results.isEmpty());
            assertEquals(1, results.size());
            verify(farmRepository).findAll(any(Specification.class));
        }
    }

    @Nested
    @DisplayName("Update Farm Tests")
    class UpdateFarmTests {

        @Test
        @DisplayName("Should update farm successfully")
        void shouldUpdateFarmSuccessfully() {
            when(farmRepository.findById(1L)).thenReturn(Optional.of(testFarm));
            when(farmRepository.save(any(Farm.class))).thenReturn(testFarm);
            when(farmMapper.toFarmResponseDto(any(Farm.class))).thenReturn(testFarmResponseDTO);

            FarmResponseDTO result = farmService.update(1L, testFarmRequestDTO);

            assertNotNull(result);
            assertEquals(testFarmResponseDTO.id(), result.id());
            verify(farmRepository).save(any(Farm.class));
        }

        @Test
        @DisplayName("Should throw EntityNotFoundException when updating non-existent farm")
        void shouldThrowExceptionWhenUpdatingNonExistentFarm() {
            when(farmRepository.findById(1L)).thenReturn(Optional.empty());

            assertThrows(EntityNotFoundException.class,
                    () -> farmService.update(1L, testFarmRequestDTO));
            verify(farmRepository, never()).save(any(Farm.class));
        }
    }
    @Nested
    @DisplayName("Delete Farm Tests")
    class DeleteFarmTests {

        @Test
        @DisplayName("Should delete farm successfully")
        void shouldDeleteFarmSuccessfully() {
            when(farmRepository.existsById(1L)).thenReturn(true);
            doNothing().when(farmRepository).deleteById(1L);

            assertDoesNotThrow(() -> farmService.deleteById(1L));
            verify(farmRepository).deleteById(1L);
        }

        @Test
        @DisplayName("Should throw EntityNotFoundException when deleting non-existent farm")
        void shouldThrowExceptionWhenDeletingNonExistentFarm() {
            when(farmRepository.existsById(1L)).thenReturn(false);

            assertThrows(EntityNotFoundException.class, () -> farmService.deleteById(1L));
            verify(farmRepository, never()).deleteById(anyLong());
        }
    }


    @Test
    @DisplayName("Should return all farms")
    void shouldReturnAllFarms() {
        List<Farm> farms = Arrays.asList(testFarm);
        when(farmRepository.findAll()).thenReturn(farms);
        when(farmMapper.toFarmResponseDto(any(Farm.class))).thenReturn(testFarmResponseDTO);

        List<FarmResponseDTO> results = farmService.findAll();

        assertNotNull(results);
        assertFalse(results.isEmpty());
        assertEquals(1, results.size());
        verify(farmRepository).findAll();
    }

}