package org.wora.citronnix.sale.application.dto.nested;

import java.math.BigDecimal;
import java.time.LocalDate;

public record SaleNestedResponse(
        Long id,
        LocalDate dateVente,
        BigDecimal prixUnitaire,
        String client,
        Double quantite,
        BigDecimal revenu
) {
}
