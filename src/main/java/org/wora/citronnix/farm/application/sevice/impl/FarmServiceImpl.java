package org.wora.citronnix.farm.application.sevice.impl;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    @Override
    public FarmResponseDTO save(FarmRequestDTO farmRequestDTO) {
        if (farmRepository.existsByNom(farmRequestDTO.nom())) {
            throw new EntityExistsException("Le nom de la ferme '" + farmRequestDTO.nom() + "' est déjà pris.");
        }

        Farm farm = farmMapper.toFarm(farmRequestDTO);

        Farm savedFarm = farmRepository.save(farm);

        return farmMapper.toFarmResponseDto(savedFarm);
    }


    @Override
    public void deleteById(Long id) {
        if (farmRepository.existsById(id)) {
            farmRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("farm not found with id :" + id);
        }
    }
}
