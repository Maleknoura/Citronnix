package org.wora.citronnix.farm.application.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.wora.citronnix.farm.application.dto.request.FarmRequestDTO;
import org.wora.citronnix.farm.application.dto.response.FarmResponseDTO;
import org.wora.citronnix.farm.domain.entity.Farm;

import java.time.LocalDate;

@Mapper(componentModel = "spring")
public interface FarmMapper {

    @Mapping(target = "dateCreation", source = "dateCreation")
    @Mapping(target = "localisation", source = "localisation")
    @Mapping(target = "nom", source = "nom")
    @Mapping(target = "superficie", source = "superficie")
    Farm toFarm(FarmRequestDTO farmRequestDTO);


    FarmResponseDTO toFarmResponseDto(Farm farm);
    void updateFarmFromDTO(FarmRequestDTO farmRequestDTO, @MappingTarget Farm farm);


    @Named("toLocalDate")
    default LocalDate toLocalDate(String date) {
        return date != null ? LocalDate.parse(date) : null;
    }

    Farm toEntity(FarmResponseDTO farmResponseDTO);
}
