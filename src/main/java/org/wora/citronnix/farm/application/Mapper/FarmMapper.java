package org.wora.citronnix.farm.application.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.wora.citronnix.farm.application.dto.request.FarmRequestDTO;
import org.wora.citronnix.farm.application.dto.response.FarmResponseDTO;
import org.wora.citronnix.farm.domain.entity.Farm;

@Mapper
public interface FarmMapper {
    FarmMapper INSTANCE = Mappers.getMapper(FarmMapper.class);

    FarmResponseDTO toFarmResponseDto(Farm farm);

    Farm toFarm(FarmRequestDTO farmRequestDTO);
}
