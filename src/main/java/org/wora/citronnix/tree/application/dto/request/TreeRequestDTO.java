package org.wora.citronnix.tree.application.dto.request;



import java.time.LocalDate;

public record TreeRequestDTO(
        Long fieldId,
        LocalDate plantationPeriod
) {

}
