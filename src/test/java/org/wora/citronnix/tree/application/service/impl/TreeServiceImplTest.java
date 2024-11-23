package org.wora.citronnix.tree.application.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import jakarta.persistence.EntityNotFoundException;
import org.wora.citronnix.farm.domain.entity.Field;
import org.wora.citronnix.farm.domain.repository.FieldRepository;
import org.wora.citronnix.farm.domain.valueObject.Superficie;
import org.wora.citronnix.tree.application.dto.request.TreeRequestDTO;
import org.wora.citronnix.tree.application.dto.response.TreeResponseDTO;
import org.wora.citronnix.tree.application.mapper.TreeMapper;
import org.wora.citronnix.tree.domain.entity.Tree;
import org.wora.citronnix.tree.domain.repository.TreeRepository;
import org.wora.citronnix.tree.domain.valueObject.PeriodePlantation;
import org.wora.citronnix.tree.domain.valueObject.TreeProductivity;

import java.time.LocalDate;
import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TreeServiceImplTest {

    @Mock
    private TreeRepository treeRepository;

    @Mock
    private FieldRepository fieldRepository;

    @Mock
    private TreeMapper treeMapper;

    @InjectMocks
    private TreeServiceImpl treeService;

    private Field testField;
    private Tree testTree;
    private TreeRequestDTO testTreeRequestDTO;
    private TreeResponseDTO testTreeResponseDTO;
    private LocalDate validPlantationDate;

    @BeforeEach
    void setUp() {
        validPlantationDate = LocalDate.of(2024, 3, 15);

        testField = Field.builder()
                .id(1L)
                .superficie(new Superficie(1.0))
                .trees(new ArrayList<>())
                .build();

        PeriodePlantation periodePlantation = new PeriodePlantation(validPlantationDate);
        TreeProductivity productivity = TreeProductivity.calculerProductivite(2);

        testTree = Tree.builder()
                .id(1L)
                .field(testField)
                .plantationPeriod(periodePlantation)
                .productivity(productivity)
                .build();

        testTreeRequestDTO = new TreeRequestDTO(1L, validPlantationDate);
        testTreeResponseDTO = new TreeResponseDTO(1L, 1L, validPlantationDate, 2, "JEUNE", 2.5);
    }

    @Nested
    @DisplayName("Save Tree Tests")
    class SaveTreeTests {

        @Test
        @DisplayName("Should save tree successfully during valid plantation period")
        void shouldSaveTreeSuccessfully() {
            when(fieldRepository.findById(1L)).thenReturn(Optional.of(testField));
            when(treeRepository.save(any(Tree.class))).thenReturn(testTree);
            when(treeMapper.toResponseDTO(any(Tree.class))).thenReturn(testTreeResponseDTO);

            TreeResponseDTO result = treeService.save(testTreeRequestDTO);

            assertNotNull(result);
            assertEquals(testTreeResponseDTO.id(), result.id());
            verify(treeRepository).save(any(Tree.class));
        }

        @Test
        @DisplayName("Should throw exception when field not found")
        void shouldThrowExceptionWhenFieldNotFound() {
            when(fieldRepository.findById(1L)).thenReturn(Optional.empty());

            assertThrows(EntityNotFoundException.class,
                    () -> treeService.save(testTreeRequestDTO));
            verify(treeRepository, never()).save(any(Tree.class));
        }

        @Test
        @DisplayName("Should throw exception when tree density exceeds limit")
        void shouldThrowExceptionWhenTreeDensityExceedsLimit() {
            List<Tree> maxTrees = new ArrayList<>();

            for (int i = 0; i < 100; i++) {
                maxTrees.add(Tree.builder()
                        .id((long) i)
                        .build());
            }

            testField.setTrees(maxTrees);
            when(fieldRepository.findById(1L)).thenReturn(Optional.of(testField));

            assertThrows(IllegalStateException.class,
                    () -> treeService.save(testTreeRequestDTO));

            verify(treeRepository, never()).save(any(Tree.class));
        }

        @Test
        @DisplayName("Should throw exception for invalid plantation period")
        void shouldThrowExceptionForInvalidPlantationPeriod() {
            LocalDate invalidDate = LocalDate.of(2024, 1, 1);
            TreeRequestDTO invalidRequest = new TreeRequestDTO(1L, invalidDate);

            when(fieldRepository.findById(1L)).thenReturn(Optional.of(testField));

            assertThrows(IllegalArgumentException.class,
                    () -> treeService.save(invalidRequest));
            verify(treeRepository, never()).save(any(Tree.class));
        }
    }

    @Nested
    @DisplayName("Update Tree Tests")
    class UpdateTreeTests {

        @Test
        @DisplayName("Should update tree successfully")
        void shouldUpdateTreeSuccessfully() {
            when(treeRepository.findById(1L)).thenReturn(Optional.of(testTree));
            when(fieldRepository.findById(1L)).thenReturn(Optional.of(testField));
            when(treeRepository.save(any(Tree.class))).thenReturn(testTree);
            when(treeMapper.toResponseDTO(any(Tree.class))).thenReturn(testTreeResponseDTO);

            TreeResponseDTO result = treeService.update(1L, testTreeRequestDTO);

            assertNotNull(result);
            assertEquals(testTreeResponseDTO.id(), result.id());
            verify(treeRepository).save(any(Tree.class));
        }

        @Test
        @DisplayName("Should throw exception when updating non-existent tree")
        void shouldThrowExceptionWhenUpdatingNonExistentTree() {
            when(treeRepository.findById(1L)).thenReturn(Optional.empty());

            assertThrows(EntityNotFoundException.class,
                    () -> treeService.update(1L, testTreeRequestDTO));
            verify(treeRepository, never()).save(any(Tree.class));
        }
    }

    @Nested
    @DisplayName("Tree Productivity Tests")
    class TreeProductivityTests {

        @Test
        @DisplayName("Should calculate correct productivity for young tree")
        void shouldCalculateCorrectProductivityForYoungTree() {
            LocalDate youngTreeDate = LocalDate.now()
                    .withMonth(3)
                    .withDayOfMonth(15)
                    .minusYears(2);

            TreeRequestDTO youngTreeRequest = new TreeRequestDTO(1L, youngTreeDate);

            when(fieldRepository.findById(1L)).thenReturn(Optional.of(testField));
            when(treeRepository.save(any(Tree.class))).thenReturn(testTree);
            when(treeMapper.toResponseDTO(any(Tree.class))).thenReturn(
                    new TreeResponseDTO(1L, 1L, youngTreeDate, 2, "JEUNE", 2.5));

            TreeResponseDTO result = treeService.save(youngTreeRequest);

            assertEquals(2.5, result.quantiteParSaison());
            assertEquals("JEUNE", result.categorieAge());
        }

        @Test
        @DisplayName("Should calculate correct productivity for mature tree")
        void shouldCalculateCorrectProductivityForMatureTree() {
            LocalDate matureTreeDate = LocalDate.now()
                    .withMonth(3)
                    .withDayOfMonth(15)
                    .minusYears(5);
            TreeRequestDTO matureTreeRequest = new TreeRequestDTO(1L, matureTreeDate);

            when(fieldRepository.findById(1L)).thenReturn(Optional.of(testField));
            when(treeRepository.save(any(Tree.class))).thenReturn(testTree);
            when(treeMapper.toResponseDTO(any(Tree.class))).thenReturn(
                    new TreeResponseDTO(1L, 1L, matureTreeDate, 5, "MATURE", 12.0));

            TreeResponseDTO result = treeService.save(matureTreeRequest);

            assertEquals(12.0, result.quantiteParSaison());
            assertEquals("MATURE", result.categorieAge());
        }
    }

    @Nested
    @DisplayName("Delete Tree Tests")
    class DeleteTreeTests {

        @Test
        @DisplayName("Should delete tree successfully")
        void shouldDeleteTreeSuccessfully() {
            when(treeRepository.existsById(1L)).thenReturn(true);
            doNothing().when(treeRepository).deleteById(1L);

            assertDoesNotThrow(() -> treeService.deleteById(1L));
            verify(treeRepository).deleteById(1L);
        }

        @Test
        @DisplayName("Should throw exception when deleting non-existent tree")
        void shouldThrowExceptionWhenDeletingNonExistentTree() {
            when(treeRepository.existsById(1L)).thenReturn(false);

            assertThrows(EntityNotFoundException.class,
                    () -> treeService.deleteById(1L));
            verify(treeRepository, never()).deleteById(1L);
        }
    }

    @Test
    @DisplayName("Should find tree by id")
    void shouldFindTreeById() {
        when(treeRepository.findById(1L)).thenReturn(Optional.of(testTree));
        when(treeMapper.toResponseDTO(testTree)).thenReturn(testTreeResponseDTO);

        TreeResponseDTO result = treeService.findById(1L);

        assertNotNull(result);
        assertEquals(testTreeResponseDTO.id(), result.id());
        verify(treeRepository).findById(1L);
    }

    @Test
    @DisplayName("Should find all trees")
    void shouldFindAllTrees() {
        List<Tree> trees = List.of(testTree);
        when(treeRepository.findAll()).thenReturn(trees);
        when(treeMapper.toResponseDTO(any(Tree.class))).thenReturn(testTreeResponseDTO);

        List<TreeResponseDTO> results = treeService.findAll();

        assertNotNull(results);
        assertFalse(results.isEmpty());
        assertEquals(1, results.size());
        verify(treeRepository).findAll();
    }
}

