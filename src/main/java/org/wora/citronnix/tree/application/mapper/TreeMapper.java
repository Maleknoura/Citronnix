package org.wora.citronnix.tree.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.wora.citronnix.tree.application.dto.request.TreeRequestDTO;
import org.wora.citronnix.tree.application.dto.response.TreeResponseDTO;
import org.wora.citronnix.tree.domain.entity.Tree;
import org.wora.citronnix.tree.domain.valueObject.PeriodePlantation;
import org.wora.citronnix.tree.domain.valueObject.TreeProductivity;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        imports = {PeriodePlantation.class, TreeProductivity.class}
)
public interface TreeMapper {
    @Mapping(source = "datePlantation.date", target = "datePlantation")
    @Mapping(source = "field.id", target = "fieldId")
    @Mapping(source = "productivity.categorieAge", target = "categorieAge")
    @Mapping(source = "productivity.quantiteParSaison", target = "quantiteParSaison")
    @Mapping(target = "age", expression = "java(tree.getAge())")
    TreeResponseDTO toResponseDTO(Tree tree);

    @Mapping(target = "datePlantation", expression = "java(new PeriodePlantation(requestDTO.datePlantation()))")
    @Mapping(
            target = "productivity",
            expression = "java(TreeProductivity.calculerProductivite(new PeriodePlantation(requestDTO.datePlantation()).getAge()))"
    )
    Tree toEntity(TreeRequestDTO requestDTO);
}