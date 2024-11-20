package org.wora.citronnix.harvest.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.wora.citronnix.harvest.application.dto.request.HarvestRequestDTO;
import org.wora.citronnix.harvest.application.dto.response.HarvestResponseDTO;
import org.wora.citronnix.harvest.domain.entity.Harvest;

@Mapper(componentModel = "spring", uses = {DetailHarvestMapper.class})
public interface HarvestMapper {
    @Mapping(target = "saison", source = "saison")
    HarvestResponseDTO toDto(Harvest harvest);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "saison", source = "saison")
    @Mapping(target = "quantiteTotale", source = "quantiteTotale")
    @Mapping(target = "sales", ignore = true)
    @Mapping(target = "detailsHarvest", ignore = true)
    Harvest toEntity(HarvestRequestDTO dto);

    void updateEntityFromDto(HarvestRequestDTO dto, @MappingTarget Harvest entity);

}