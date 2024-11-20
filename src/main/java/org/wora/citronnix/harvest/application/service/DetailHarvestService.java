package org.wora.citronnix.harvest.application.service;

import org.wora.citronnix.harvest.application.dto.request.DetailHarvestRequestDTO;
import org.wora.citronnix.harvest.application.dto.response.DetailHarvestResponseDTO;
import org.wora.citronnix.harvest.domain.embeddable.HarvestDetailId;
import org.wora.citronnix.harvest.domain.entity.DetailHarvest;
import org.wora.citronnix.shared.service.GenericService;

public interface DetailHarvestService extends GenericService<DetailHarvest, HarvestDetailId, DetailHarvestRequestDTO, DetailHarvestResponseDTO> {
}
