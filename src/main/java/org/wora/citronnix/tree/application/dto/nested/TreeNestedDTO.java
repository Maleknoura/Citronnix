package org.wora.citronnix.tree.application.dto.nested;

import jakarta.persistence.Embedded;
import org.wora.citronnix.tree.domain.valueObject.PeriodePlantation;

import java.time.LocalDate;

public record TreeNestedDTO(    Long id,
                                LocalDate plantationPeriod,
                                int age,
                                String categorieAge,
                                Double quantiteParSaison
) {
}
