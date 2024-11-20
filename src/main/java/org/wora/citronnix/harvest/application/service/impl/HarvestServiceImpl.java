package org.wora.citronnix.harvest.application.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wora.citronnix.farm.domain.entity.Field;
import org.wora.citronnix.farm.domain.repository.FieldRepository;
import org.wora.citronnix.harvest.application.dto.request.HarvestRequestDTO;
import org.wora.citronnix.harvest.application.dto.response.HarvestResponseDTO;
import org.wora.citronnix.harvest.application.mapper.HarvestMapper;
import org.wora.citronnix.harvest.application.service.HarvestService;
import org.wora.citronnix.harvest.domain.entity.Harvest;
import org.wora.citronnix.harvest.domain.repository.HarvestRepository;
import org.wora.citronnix.tree.domain.repository.TreeRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class HarvestServiceImpl implements HarvestService {
    private final TreeRepository treeRepository;
    private final FieldRepository fieldRepository;
    private final HarvestRepository harvestRepository;
    private final HarvestMapper harvestMapper;

    @Override
    public HarvestResponseDTO findById(Long id) {
        Harvest harvest = harvestRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Harvest not found for id: " + id));

        return harvestMapper.toDto(harvest);
    }

    @Override
    public List<HarvestResponseDTO> findAll() {
        List<Harvest> harvests = harvestRepository.findAll();

        return harvests.stream()
                .map(harvestMapper::toDto)
                .collect(Collectors.toList());
    }

    public HarvestResponseDTO save(HarvestRequestDTO requestDTO) {

        Field field = fieldRepository.findById(requestDTO.fieldId())
                .orElseThrow(() -> new EntityNotFoundException("Field not found"));
        Harvest harvest = harvestMapper.toEntity(requestDTO);
        Harvest savedHarvest = harvestRepository.save(harvest);
        return harvestMapper.toDto(savedHarvest);
    }


    @Override
    public void deleteById(Long id) {
        if (!harvestRepository.existsById(id)) {
            throw new EntityNotFoundException("Harvest not found for id: " + id);
        }
        harvestRepository.deleteById(id);
    }
    @Transactional
    public HarvestResponseDTO update(Long id, HarvestRequestDTO requestDTO) {
        Harvest existingHarvest = harvestRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Harvest not found"));

        Field field = fieldRepository.findById(requestDTO.fieldId())
                .orElseThrow(() -> new EntityNotFoundException("Field not found"));

        Harvest updatedHarvest = harvestMapper.toEntity(requestDTO);
        updatedHarvest.setId(id);
        updatedHarvest = harvestRepository.save(updatedHarvest);

        return harvestMapper.toDto(updatedHarvest);
    }


}
