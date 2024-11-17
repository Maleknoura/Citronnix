package org.wora.citronnix.farm.application.dto.request;

import jakarta.validation.constraints.Positive;
import org.wora.citronnix.farm.domain.valueObject.Superficie;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FarmRequestDTO(
        @NotBlank(message = "Le nom de la ferme ne peut pas être vide") String nom,

        @NotBlank(message = "La localisation ne peut pas être vide") String localisation,

        @NotNull(message = "La superficie ne peut pas être nulle")
        @Positive(message = "la superficie doit etre positive")
        Superficie superficie,

        @NotNull(message = "La date de création ne peut pas être nulle") LocalDate dateCreation
) {
}

