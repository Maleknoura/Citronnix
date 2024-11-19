package org.wora.citronnix.farm.application.sevice;

import org.wora.citronnix.farm.application.dto.request.FieldRequestDTO;
import org.wora.citronnix.farm.application.dto.response.FieldResponseDTO;
import org.wora.citronnix.farm.domain.entity.Field;
import org.wora.citronnix.shared.service.GenericService;

public interface FieldService extends GenericService<Field,Long, FieldRequestDTO, FieldResponseDTO> {
}
