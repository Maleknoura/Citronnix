package org.wora.citronnix.sale.application.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

public record SaleResponseDTO(Long id,
                              LocalDate dateVente,
                              BigDecimal prixUnitaire,
                              String client,
                              Long harvestId,
                              Double quantite,
                              BigDecimal revenu) {
}
