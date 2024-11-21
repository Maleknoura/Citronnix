package org.wora.citronnix.harvest.application.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wora.citronnix.harvest.application.dto.request.DetailHarvestRequestDTO;
import org.wora.citronnix.harvest.application.dto.response.DetailHarvestResponseDTO;
import org.wora.citronnix.harvest.application.service.DetailHarvestService;
import org.wora.citronnix.harvest.domain.embeddable.HarvestDetailId;
import org.wora.citronnix.harvest.domain.entity.DetailHarvest;
import org.wora.citronnix.harvest.domain.entity.Harvest;
import org.wora.citronnix.harvest.domain.repository.DetailHarvestRepository;
import org.wora.citronnix.harvest.domain.repository.HarvestRepository;
import org.wora.citronnix.tree.domain.entity.Tree;
import org.wora.citronnix.tree.domain.repository.TreeRepository;
import org.wora.citronnix.harvest.application.mapper.DetailHarvestMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DetailHarvestServiceImpl implements DetailHarvestService {

    private final HarvestRepository harvestRepository;
    private final TreeRepository treeRepository;
    private final DetailHarvestRepository detailHarvestRepository;
    private final DetailHarvestMapper detailHarvestMapper;


    @Override
    public DetailHarvestResponseDTO findById(HarvestDetailId harvestDetailId) {
        DetailHarvest detailHarvest = detailHarvestRepository.findById(harvestDetailId)
                .orElseThrow(() -> new EntityNotFoundException("Detail Harvest not found"));

        return detailHarvestMapper.toDetailHarvestResponseDTO(detailHarvest);
    }


    @Override
    public List<DetailHarvestResponseDTO> findAll() {
        List<DetailHarvest> detailHarvestList = detailHarvestRepository.findAll();

        return detailHarvestList.stream()
                .map(detailHarvestMapper::toDetailHarvestResponseDTO)
                .collect(Collectors.toList());
    }


    @Override
    @Transactional
    public DetailHarvestResponseDTO save(DetailHarvestRequestDTO requestDTO) {
        Harvest harvest = harvestRepository.findById(requestDTO.harvestId())
                .orElseThrow(() -> new EntityNotFoundException("Harvest not found"));

        Tree tree = treeRepository.findById(requestDTO.treeId())
                .orElseThrow(() -> new EntityNotFoundException("Tree not found"));

        DetailHarvest detailHarvest = detailHarvestMapper.toDetailHarvest(requestDTO);
        HarvestDetailId id = new HarvestDetailId(harvest.getId(), tree.getId());
        detailHarvest.setId(id);

        harvest.updateTotalQuantity();
        DetailHarvest savedDetailHarvest = detailHarvestRepository.save(detailHarvest);

        return detailHarvestMapper.toDetailHarvestResponseDTO(savedDetailHarvest);
    }

    @Override
    @Transactional
    public void deleteById(HarvestDetailId harvestDetailId) {
        DetailHarvest detailHarvest = detailHarvestRepository.findById(harvestDetailId)
                .orElseThrow(() -> new EntityNotFoundException("Detail Harvest not found"));

        detailHarvestRepository.delete(detailHarvest);
    }


    @Override
    public DetailHarvestResponseDTO update(HarvestDetailId harvestDetailId, DetailHarvestRequestDTO detailHarvestRequestDTO) {
        return null;
    }


}
