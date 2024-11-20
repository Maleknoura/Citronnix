package org.wora.citronnix.sale.application.service;

import org.wora.citronnix.sale.application.dto.request.SaleRequestDTO;
import org.wora.citronnix.sale.application.dto.response.SaleResponseDTO;
import org.wora.citronnix.sale.domain.entity.Sale;
import org.wora.citronnix.shared.service.GenericService;

public interface SaleService extends GenericService<Sale,Long, SaleRequestDTO, SaleResponseDTO> {
}
