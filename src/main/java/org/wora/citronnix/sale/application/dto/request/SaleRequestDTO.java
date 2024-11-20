package org.wora.citronnix.sale.application.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;

public record SaleRequestDTO(
        LocalDate dateVente,
        BigDecimal prixUnitaire,
        String client,
        Long harvestId,
        Double quantite
) {
}
