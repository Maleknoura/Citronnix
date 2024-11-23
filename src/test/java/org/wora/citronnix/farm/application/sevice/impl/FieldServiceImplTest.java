
package org.wora.citronnix.farm.application.sevice.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import jakarta.persistence.EntityNotFoundException;
import org.wora.citronnix.farm.application.Mapper.FarmMapper;
import org.wora.citronnix.farm.application.Mapper.FieldMapper;
import org.wora.citronnix.farm.application.dto.request.FieldRequestDTO;
import org.wora.citronnix.farm.application.dto.response.FarmResponseDTO;
import org.wora.citronnix.farm.application.dto.response.FieldResponseDTO;
import org.wora.citronnix.farm.domain.entity.Farm;
import org.wora.citronnix.farm.domain.entity.Field;
import org.wora.citronnix.farm.domain.repository.FieldRepository;
import org.wora.citronnix.farm.domain.valueObject.FieldSpecification;
import org.wora.citronnix.farm.domain.valueObject.Superficie;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FieldServiceImplTest {

    @Mock
    private FieldRepository fieldRepository;

    @Mock
    private FarmServiceImpl farmService;

    @Mock
    private FieldMapper fieldMapper;

    @Mock
    private FarmMapper farmMapper;

    @InjectMocks
    private FieldServiceImpl fieldService;

    private Field testField;
    private Farm testFarm;
    private FieldRequestDTO testFieldRequestDTO;
    private FieldResponseDTO testFieldResponseDTO;
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

        testField = Field.builder()
                .id(1L)
                .superficie(new Superficie(10.0))
                .farm(testFarm)
                .build();

        testFieldRequestDTO = new FieldRequestDTO(10.0, 1L);

        testFarmResponseDTO = new FarmResponseDTO(
                1L, "Test Farm", "Test Location",
                100.0, LocalDate.now(), List.of()
        );

        testFieldResponseDTO = new FieldResponseDTO(
                1L, 10.0, 1L, List.of()
        );
    }

    @Nested
    @DisplayName("Find By ID Tests")
    class FindByIdTests {

        @Test
        @DisplayName("Should return field when exists")
        void shouldReturnFieldWhenExists() {
            when(fieldRepository.findById(1L)).thenReturn(Optional.of(testField));
            when(fieldMapper.toResponseDTO(testField)).thenReturn(testFieldResponseDTO);

            FieldResponseDTO result = fieldService.findById(1L);

            assertNotNull(result);
            assertEquals(testFieldResponseDTO.id(), result.id());
            verify(fieldRepository).findById(1L);
        }

        @Test
        @DisplayName("Should throw EntityNotFoundException when field doesn't exist")
        void shouldThrowExceptionWhenFieldNotFound() {
            when(fieldRepository.findById(1L)).thenReturn(Optional.empty());

            assertThrows(EntityNotFoundException.class, () -> fieldService.findById(1L));
            verify(fieldRepository).findById(1L);
        }
    }

    @Nested
    @DisplayName("Save Field Tests")
    class SaveFieldTests {

        @Test
        @DisplayName("Should save field successfully")
        void shouldSaveFieldSuccessfully() {
            when(farmService.findById(1L)).thenReturn(testFarmResponseDTO);
            when(farmMapper.toEntity(testFarmResponseDTO)).thenReturn(testFarm);
            when(fieldMapper.toEntity(testFieldRequestDTO, testFarm)).thenReturn(testField);
            when(fieldRepository.save(any(Field.class))).thenReturn(testField);
            when(fieldMapper.toResponseDTO(testField)).thenReturn(testFieldResponseDTO);

            FieldResponseDTO result = fieldService.save(testFieldRequestDTO);

            assertNotNull(result);
            assertEquals(testFieldResponseDTO.superficie(), result.superficie());
            verify(fieldRepository).save(any(Field.class));
        }

        @Test
        @DisplayName("Should throw EntityNotFoundException when farm not found")
        void shouldThrowExceptionWhenFarmNotFound() {
            when(farmService.findById(1L)).thenReturn(null);

            assertThrows(EntityNotFoundException.class,
                    () -> fieldService.save(testFieldRequestDTO));
            verify(fieldRepository, never()).save(any(Field.class));
        }
    }

    @Nested
    @DisplayName("Update Field Tests")
    class UpdateFieldTests {

        @Test
        @DisplayName("Should update field successfully")
        void shouldUpdateFieldSuccessfully() {
            when(fieldRepository.findById(1L)).thenReturn(Optional.of(testField));
            when(farmService.findById(1L)).thenReturn(testFarmResponseDTO);
            when(farmMapper.toEntity(testFarmResponseDTO)).thenReturn(testFarm);
            when(fieldMapper.toEntity(testFieldRequestDTO, testFarm)).thenReturn(testField);
            when(fieldRepository.save(any(Field.class))).thenReturn(testField);
            when(fieldMapper.toResponseDTO(testField)).thenReturn(testFieldResponseDTO);

            FieldResponseDTO result = fieldService.update(1L, testFieldRequestDTO);

            assertNotNull(result);
            assertEquals(testFieldResponseDTO.id(), result.id());
            verify(fieldRepository).save(any(Field.class));
        }

        @Test
        @DisplayName("Should throw EntityNotFoundException when updating non-existent field")
        void shouldThrowExceptionWhenUpdatingNonExistentField() {
            when(fieldRepository.findById(1L)).thenReturn(Optional.empty());

            assertThrows(EntityNotFoundException.class,
                    () -> fieldService.update(1L, testFieldRequestDTO));
            verify(fieldRepository, never()).save(any(Field.class));
        }
    }

    @Nested
    @DisplayName("Delete Field Tests")
    class DeleteFieldTests {

        @Test
        @DisplayName("Should delete field successfully")
        void shouldDeleteFieldSuccessfully() {
            when(fieldRepository.existsById(1L)).thenReturn(true);
            doNothing().when(fieldRepository).deleteById(1L);

            assertDoesNotThrow(() -> fieldService.deleteById(1L));
            verify(fieldRepository).deleteById(1L);
        }

        @Test
        @DisplayName("Should throw EntityNotFoundException when deleting non-existent field")
        void shouldThrowExceptionWhenDeletingNonExistentField() {
            when(fieldRepository.existsById(1L)).thenReturn(false);

            assertThrows(EntityNotFoundException.class, () -> fieldService.deleteById(1L));
            verify(fieldRepository, never()).deleteById(anyLong());
        }
    }

    @Test
    @DisplayName("Should return all fields")
    void shouldReturnAllFields() {
        List<Field> fields = Arrays.asList(testField);
        when(fieldRepository.findAll()).thenReturn(fields);
        when(fieldMapper.toResponseDTO(any(Field.class))).thenReturn(testFieldResponseDTO);

        List<FieldResponseDTO> results = fieldService.findAll();

        assertNotNull(results);
        assertFalse(results.isEmpty());
        assertEquals(1, results.size());
        verify(fieldRepository).findAll();
    }

    @Nested
    @DisplayName("Field Specification Tests")
    class FieldSpecificationTests {

        @Test
        @DisplayName("Should validate field specification successfully")
        void shouldValidateFieldSpecificationSuccessfully() {
            when(fieldRepository.countByFarm(testFarm)).thenReturn(5L);
            when(fieldRepository.findByFarm(testFarm))
                    .thenReturn(List.of(Field.builder()
                            .superficie(new Superficie(20.0))
                            .build()));

            assertDoesNotThrow(() ->
                    new FieldSpecification(new Superficie(10.0), testFarm, fieldRepository));
        }

        @Test
        @DisplayName("Should throw exception when farm has maximum fields")
        void shouldThrowExceptionWhenFarmHasMaximumFields() {
            when(fieldRepository.countByFarm(testFarm)).thenReturn(10L);

            assertThrows(IllegalStateException.class, () ->
                    new FieldSpecification(new Superficie(10.0), testFarm, fieldRepository));
        }

        @Test
        @DisplayName("Should throw exception when total superficie exceeds farm limite")
        void shouldThrowExceptionWhenTotalSuperficieExceedsFarmLimit() {
            when(fieldRepository.countByFarm(testFarm)).thenReturn(5L);
            when(fieldRepository.findByFarm(testFarm))
                    .thenReturn(List.of(Field.builder()
                            .superficie(new Superficie(80.0))
                            .build()));

            assertThrows(IllegalArgumentException.class, () ->
                    new FieldSpecification(new Superficie(30.0), testFarm, fieldRepository));
        }
    }
}
