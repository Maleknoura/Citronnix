package org.wora.citronnix.farm.application.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FarmRequestDTO(
                @NotBlank(message = "Le nom de la ferme ne peut pas être vide")
        String nom,

        @NotBlank(message = "La localisation ne peut pas être vide")
        String localisation,

        @NotNull(message = "La superficie est obligatoire")
        Double superficie,

        @NotNull(message = "La date de création ne peut pas être nulle")
        LocalDate dateCreation
) {
}

