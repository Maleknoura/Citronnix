package org.wora.citronnix.farm.application.sevice.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wora.citronnix.farm.application.Mapper.FarmMapper;
import org.wora.citronnix.farm.application.Mapper.FieldMapper;
import org.wora.citronnix.farm.application.dto.request.FieldRequestDTO;
import org.wora.citronnix.farm.application.dto.response.FarmResponseDTO;
import org.wora.citronnix.farm.application.dto.response.FieldResponseDTO;
import org.wora.citronnix.farm.application.sevice.FieldService;
import org.wora.citronnix.farm.domain.entity.Farm;
import org.wora.citronnix.farm.domain.entity.Field;
import org.wora.citronnix.farm.domain.repository.FieldRepository;
import org.wora.citronnix.farm.domain.valueObject.FieldSpecification;

import java.util.List;
@Service
@AllArgsConstructor
public class FieldServiceImpl implements FieldService {
    private final FieldRepository fieldRepository;
    private final FarmServiceImpl farmService;

    private final FieldMapper fieldMapper;
    private final FarmMapper farmMapper;
    @Override
    public FieldResponseDTO findById(Long aLong) {
        return null;
    }
    @Transactional
    @Override
    public FieldResponseDTO save(FieldRequestDTO fieldRequestDTO) {
        FarmResponseDTO farmResponseDTO = farmService.findById(fieldRequestDTO.farmId());
        if (farmResponseDTO == null) {
            throw new EntityNotFoundException("Ferme non trouvée");
        }

        Farm farm = farmMapper.toEntity(farmResponseDTO);
        Field field = fieldMapper.toEntity(fieldRequestDTO, farm);

        if (field.getId() != null) {
            field.setId(null);
        }

        new FieldSpecification(field.getSuperficie(), farm,fieldRepository);

        Field savedField = fieldRepository.save(field);
        return fieldMapper.toResponseDTO(savedField);
    }
        @Override
    public List<FieldResponseDTO> findAll() {
        return List.of();
    }



    @Override
    public void deleteById(Long id) {
        if (fieldRepository.existsById(id)) {
            fieldRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Champ non trouvé avec l'id : " + id);
        }
    }


    @Override
    public FieldResponseDTO update(Long aLong, FieldRequestDTO fieldRequestDTO) {
        return null;
    }
}
