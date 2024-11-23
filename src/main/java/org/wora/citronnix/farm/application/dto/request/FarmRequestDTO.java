package org.wora.citronnix.farm.application.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record FarmRequestDTO(
        @NotBlank(message = "Le nom de la ferme ne peut pas être vide")
        String nom,
        @NotBlank(message = "La localisation ne peut pas être vide")
        String localisation,

        @NotNull(message = "La superficie est obligatoire")
        @Positive(message = "La superficie doit être un nombre positif")
        Double superficie,

        @NotNull(message = "La date de création ne peut pas être nulle")
        @PastOrPresent(message = "La date de création ne peut pas être dans le futur")

        LocalDate dateCreation
) {
}

