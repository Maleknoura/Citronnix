package org.wora.citronnix.farm.application.sevice;

import org.wora.citronnix.farm.application.dto.request.FarmRequestDTO;
import org.wora.citronnix.farm.application.dto.response.FarmResponseDTO;
import org.wora.citronnix.farm.domain.entity.Farm;
import org.wora.citronnix.shared.service.GenericService;

import java.time.LocalDate;
import java.util.List;

public interface FarmService extends GenericService<Farm,Long, FarmRequestDTO, FarmResponseDTO> {
   List<Farm> searchFarms(String nom, String localisation, Double minSuperficie, Double maxSuperficie, LocalDate startDate, LocalDate endDate);
}
