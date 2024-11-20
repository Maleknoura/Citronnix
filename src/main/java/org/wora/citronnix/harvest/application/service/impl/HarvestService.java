package org.wora.citronnix.harvest.application.service.impl;

import org.wora.citronnix.harvest.application.dto.request.HarvestRequestDTO;
import org.wora.citronnix.harvest.application.dto.response.HarvestResponseDTO;
import org.wora.citronnix.harvest.domain.entity.Harvest;
import org.wora.citronnix.shared.service.GenericService;
import org.wora.citronnix.tree.domain.entity.Tree;

public interface HarvestService extends GenericService<Harvest,Long, HarvestRequestDTO, HarvestResponseDTO> {
}
