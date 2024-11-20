package org.wora.citronnix.harvest.application.service;

import org.wora.citronnix.harvest.application.dto.request.HarvestRequestDTO;
import org.wora.citronnix.harvest.application.dto.response.HarvestResponseDTO;
import org.wora.citronnix.harvest.domain.entity.Harvest;
import org.wora.citronnix.shared.service.GenericService;

public interface HarvestService extends GenericService<Harvest,Long, HarvestRequestDTO, HarvestResponseDTO> {
}
