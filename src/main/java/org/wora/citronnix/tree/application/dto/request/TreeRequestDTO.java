package org.wora.citronnix.tree.application.dto.request;



import jakarta.persistence.Embedded;
import org.wora.citronnix.tree.domain.valueObject.PeriodePlantation;

import java.time.LocalDate;

public record TreeRequestDTO(
        Long fieldId,
        LocalDate plantationPeriod
) {

}
