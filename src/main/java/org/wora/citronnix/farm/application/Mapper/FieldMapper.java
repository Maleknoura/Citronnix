package org.wora.citronnix.farm.application.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.wora.citronnix.farm.application.dto.request.FieldRequestDTO;
import org.wora.citronnix.farm.application.dto.response.FieldResponseDTO;
import org.wora.citronnix.farm.domain.entity.Farm;
import org.wora.citronnix.farm.domain.entity.Field;
import org.wora.citronnix.tree.application.mapper.TreeMapper;
import org.wora.citronnix.tree.domain.valueObject.PeriodePlantation;

import java.time.LocalDate;

@Mapper(componentModel = "spring", uses = {SuperficieMapper.class, TreeMapper.class})
public interface FieldMapper {

    @Mapping(target = "farm", source = "farm")
    @Mapping(target = "superficie", source = "dto.superficie")
    Field toEntity(FieldRequestDTO dto, Farm farm);

    @Mapping(target = "farmId", source = "farm.id")
    @Mapping(target = "superficie", source = "superficie.valeurEnHectares")
    @Mapping(target = "trees", source = "trees")

    FieldResponseDTO toResponseDTO(Field field);

    default LocalDate map(PeriodePlantation periodePlantation) {
        return periodePlantation != null ? periodePlantation.getDate() : null;
    }
}

