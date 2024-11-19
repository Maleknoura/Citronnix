package org.wora.citronnix.farm.application.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record SuperficieDTO(
        @NotNull(message = "La valeur en hectares est obligatoire")
        @Min(value = 0, message = "La valeur en hectares doit être positive")
        Double valeurEnHectares,

        @NotNull(message = "La superficie totale de la ferme est obligatoire")
        @Min(value = 0, message = "La superficie totale doit être positive")
        Double superficieTotaleFerme
) {
}
