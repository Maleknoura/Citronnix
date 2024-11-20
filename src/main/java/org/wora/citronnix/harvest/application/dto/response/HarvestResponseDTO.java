package org.wora.citronnix.harvest.application.dto.response;

import org.wora.citronnix.harvest.domain.enums.Season;

import java.time.LocalDate;
import java.util.Set;

public record HarvestResponseDTO(
        Long id,
        LocalDate dateRecolte,
        Season saison,
        Double quantiteTotale

) {
}
