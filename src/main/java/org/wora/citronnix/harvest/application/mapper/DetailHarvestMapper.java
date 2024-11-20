package org.wora.citronnix.harvest.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.wora.citronnix.harvest.application.dto.request.DetailHarvestRequestDTO;
import org.wora.citronnix.harvest.application.dto.response.DetailHarvestResponseDTO;
import org.wora.citronnix.harvest.domain.entity.DetailHarvest;

@Mapper(componentModel = "spring")
public interface DetailHarvestMapper {
    @Mapping(target = "treeId", source = "tree.id")
    @Mapping(target = "quantite", source = "quantite")
    DetailHarvestResponseDTO toDto(DetailHarvest detailHarvest);
}
