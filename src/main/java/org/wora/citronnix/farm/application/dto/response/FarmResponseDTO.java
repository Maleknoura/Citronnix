package org.wora.citronnix.farm.application.dto.response;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.wora.citronnix.farm.domain.valueObject.Superficie;

import java.time.LocalDate;

public record FarmResponseDTO(
        Long id,
        String nom,
        @NotNull(message = "La localisation ne peut pas être nulle")
        String localisation,
        Superficie superficie,
        @Past(message = "La date de création doit être dans le passé")
        LocalDate dateCreation
) {
}
