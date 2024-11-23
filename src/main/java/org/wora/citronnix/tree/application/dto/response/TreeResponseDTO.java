package org.wora.citronnix.tree.application.dto.response;

import org.wora.citronnix.farm.application.dto.nested.FieldNestedDTO;

import java.time.LocalDate;

public record TreeResponseDTO(
        Long id,
        LocalDate plantationPeriod,
        int age,
        String categorieAge,
        Double quantiteParSaison,
        FieldNestedDTO field
) {
}
