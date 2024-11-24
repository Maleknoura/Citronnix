package org.wora.citronnix.sale.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public record SaleRequestDTO(
        @NotNull(message = "La date de vente ne peut pas être nulle.") @PastOrPresent(message = "La date de vente doit être aujourd'hui ou dans le passé.") LocalDate dateVente,
        @NotNull(message = "Le prix unitaire ne peut pas être nul.") @Positive(message = "Le prix unitaire doit être un nombre positif.") BigDecimal prixUnitaire,
        @NotBlank(message = "Le nom du client ne peut pas être vide.") String client,
        @NotNull(message = "L'ID de la récolte ne peut pas être nul.") Long harvestId,
        @Positive(message = "La quantité doit être un nombre positif.") Double quantite) {
}
