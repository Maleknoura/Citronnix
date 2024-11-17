package org.wora.citronnix.farm.application.sevice.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.wora.citronnix.farm.application.Mapper.FarmMapper;
import org.wora.citronnix.farm.application.dto.request.FarmRequestDTO;
import org.wora.citronnix.farm.application.dto.response.FarmResponseDTO;
import org.wora.citronnix.farm.application.sevice.FarmService;
import org.wora.citronnix.farm.domain.repository.FarmRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FarmServiceImpl implements FarmService {
    private final FarmRepository farmRepository;
    private final FarmMapper farmMapper;

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
        return null;
    }

    @Override
    public void deleteById(Long aLong) {

    }
}
