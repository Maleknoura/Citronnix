package org.wora.citronnix.harvest.application.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record DetailHarvestRequestDTO
(    @NotNull(message = "L'ID de la récolte ne peut pas être nul.") Long harvestId,
     @NotNull(message = "L'ID de l'arbre ne peut pas être nul.") Long treeId,
     @Positive(message = "La quantité doit être un nombre positif.") Double quantite
){}
