package org.wora.citronnix.farm.application.sevice;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.wora.citronnix.farm.application.dto.request.FarmRequestDTO;
import org.wora.citronnix.farm.application.dto.request.FarmSearchCriteria;
import org.wora.citronnix.farm.application.dto.response.FarmResponseDTO;
import org.wora.citronnix.farm.domain.entity.Farm;
import org.wora.citronnix.shared.service.GenericService;

import java.time.LocalDate;
import java.util.List;

public interface FarmService extends GenericService<Farm,Long, FarmRequestDTO, FarmResponseDTO> {

    Page<FarmResponseDTO> searchFarms(FarmSearchCriteria criteria, Pageable pageable);
}
