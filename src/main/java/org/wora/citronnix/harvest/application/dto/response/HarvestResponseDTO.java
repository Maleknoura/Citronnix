package org.wora.citronnix.harvest.application.dto.response;

import org.wora.citronnix.harvest.domain.enums.Season;
import org.wora.citronnix.sale.application.dto.nested.SaleNestedResponse;
import org.wora.citronnix.sale.application.dto.response.SaleResponseDTO;
import org.wora.citronnix.tree.application.dto.nested.TreeNestedDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public record HarvestResponseDTO(
        Long id,
        LocalDate dateRecolte,
        Season saison,
        Double quantiteTotale,
        List<SaleNestedResponse> sales

) {
}
