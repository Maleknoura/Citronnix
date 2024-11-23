package org.wora.citronnix.tree.application.dto.nested;

import java.time.LocalDate;

public record TreeNestedDTO(Long id,
                            Long fieldId,
                            LocalDate plantationPeriod,
                            int age,
                            String categorieAge,
                            Double quantiteParSaison) {
}
