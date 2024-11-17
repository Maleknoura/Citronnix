package org.wora.citronnix.farm.application.sevice;

import org.wora.citronnix.farm.application.dto.request.FarmRequestDTO;
import org.wora.citronnix.farm.application.dto.response.FarmResponseDTO;
import org.wora.citronnix.farm.domain.entity.Farm;
import org.wora.citronnix.shared.service.GenericService;

public interface FarmService extends GenericService<Farm,Long, FarmRequestDTO, FarmResponseDTO> {
}
