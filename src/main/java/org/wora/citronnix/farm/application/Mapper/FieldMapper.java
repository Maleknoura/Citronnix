package org.wora.citronnix.farm.application.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.wora.citronnix.farm.application.dto.request.FieldRequestDTO;
import org.wora.citronnix.farm.application.dto.response.FieldResponseDTO;
import org.wora.citronnix.farm.domain.entity.Farm;
import org.wora.citronnix.farm.domain.entity.Field;

@Mapper(componentModel = "spring", uses = {SuperficieMapper.class})
public interface FieldMapper {

    @Mapping(target = "farm", source = "farm")
    @Mapping(target = "superficie", source = "dto.superficie")
    Field toEntity(FieldRequestDTO dto, Farm farm);

    @Mapping(target = "farmId", source = "farm.id")
    @Mapping(target = "superficie", source = "superficie.valeurEnHectares")
    FieldResponseDTO toResponseDTO(Field field);
}

