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
import org.wora.citronnix.harvest.domain.entity.Harvest;
import org.wora.citronnix.harvest.domain.repository.HarvestRepository;
import org.wora.citronnix.harvest.domain.valueObject.HarvestDetails;
import org.wora.citronnix.tree.domain.entity.Tree;
import org.wora.citronnix.tree.domain.repository.TreeRepository;

import java.util.List;
import java.util.Set;
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
    public HarvestResponseDTO findById(Long aLong) {
        return null;
    }

    @Override
    public List<HarvestResponseDTO> findAll() {
        return List.of();
    }

    public HarvestResponseDTO save(HarvestRequestDTO requestDTO) {

        Field field = fieldRepository.findById(requestDTO.fieldId())
                .orElseThrow(() -> new EntityNotFoundException("Field not found"));
        Harvest harvest = harvestMapper.toEntity(requestDTO);
        Harvest savedHarvest = harvestRepository.save(harvest);
        return harvestMapper.toDto(savedHarvest);
    }


    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public HarvestResponseDTO update(Long aLong, HarvestRequestDTO harvestRequestDTO) {
        return null;
    }
}
