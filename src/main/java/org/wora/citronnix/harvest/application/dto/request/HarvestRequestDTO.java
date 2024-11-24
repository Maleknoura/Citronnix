package org.wora.citronnix.harvest.application.dto.request;

import jakarta.validation.constraints.NotNull;
import org.wora.citronnix.harvest.domain.enums.Season;

import java.time.LocalDate;
import java.util.Set;

public record HarvestRequestDTO (
        @NotNull(message = "La date de récolte ne peut pas être nulle.")
        LocalDate dateRecolte,

        @NotNull(message = "La saison ne peut pas être nulle.")
        Season saison
    ){}
