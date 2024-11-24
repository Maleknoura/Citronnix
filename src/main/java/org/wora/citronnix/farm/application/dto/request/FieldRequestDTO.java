package org.wora.citronnix.farm.application.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record FieldRequestDTO(
        @NotNull(message = "La superficie ne peut pas être nulle")
        @Min(value = 0, message = "La superficie doit être supérieure ou égale à 0")
        Double superficie,

        @NotNull(message = "Le farmId ne peut pas être nul")
        Long farmId
) {
}
