package org.wora.citronnix.sale.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.wora.citronnix.sale.application.dto.request.SaleRequestDTO;
import org.wora.citronnix.sale.application.dto.response.SaleResponseDTO;
import org.wora.citronnix.sale.domain.entity.Sale;

@Mapper(componentModel = "spring")
public interface SaleMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "harvest", ignore = true)
    Sale toSale(SaleRequestDTO requestDTO);

    @Mapping(source = "harvest.id", target = "harvestId")
    @Mapping(source = "revenu", target = "revenu")
    SaleResponseDTO toSaleResponseDTO(Sale sale);
}
