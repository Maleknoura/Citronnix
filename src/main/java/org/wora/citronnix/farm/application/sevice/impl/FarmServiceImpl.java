package org.wora.citronnix.farm.application.sevice.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.wora.citronnix.farm.application.Mapper.FarmMapper;
import org.wora.citronnix.farm.application.dto.request.FarmRequestDTO;
import org.wora.citronnix.farm.application.dto.response.FarmResponseDTO;
import org.wora.citronnix.farm.application.sevice.FarmService;
import org.wora.citronnix.farm.domain.entity.Farm;
import org.wora.citronnix.farm.domain.repository.FarmRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Validated
public class FarmServiceImpl implements FarmService {
    private final FarmRepository farmRepository;
    private final FarmMapper farmMapper;
    @Autowired
    public FarmServiceImpl(FarmRepository farmRepository, FarmMapper farmMapper) {
        this.farmRepository = farmRepository;
        this.farmMapper = farmMapper;
    }
    @Override
    public FarmResponseDTO findById(Long aLong) {
        return null;
    }

    @Override
    public List<FarmResponseDTO> findAll() {
        return farmRepository.findAll().stream()
                .map(farmMapper::toFarmResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public FarmResponseDTO save(FarmRequestDTO farmRequestDTO) {
        System.out.println("Request to save Farm : {}"+ farmRequestDTO);

        try {
            Farm farm = farmMapper.toFarm(farmRequestDTO);

            // Ensure dateCreation is set
            if (farm.getDateCreation() == null && farmRequestDTO.dateCreation() != null) {
                farm.setDateCreation(farmRequestDTO.dateCreation());
            }

            Farm savedFarm = farmRepository.save(farm);
            return farmMapper.toFarmResponseDto(savedFarm);
        } catch (Exception e) {
            System.out.println("Error saving farm: {}"+ e.getMessage());
            throw new RuntimeException("Failed to save farm: " + e.getMessage());
        }
    }

    @Override
    public void deleteById(Long aLong) {

    }
}



