package org.wora.citronnix.tree.application.dto.response;

import java.time.LocalDate;

public record TreeResponseDTO(
        Long id,
        Long fieldId,
        LocalDate datePlantation,
        int age,
        String categorieAge,
        Double quantiteParSaison
) {
}