package org.wora.citronnix.tree.application.dto.request;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record TreeRequestDTO(
        @NotNull(message = "L'ID du champ ne peut pas être nul.") Long fieldId,
        @NotNull(message = "La période de plantation ne peut pas être nulle.")
        @PastOrPresent(message = "La période de plantation doit être dans le passé ou aujourd'hui.") LocalDate plantationPeriod
) {

}
