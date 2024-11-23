package org.wora.citronnix.farm.application.dto.response;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.wora.citronnix.farm.application.dto.nested.FieldNestedDTO;

import java.time.LocalDate;
import java.util.List;

public record FarmResponseDTO(
        Long id,
        String nom,
        String localisation,
        Double superficie,
        LocalDate dateCreation,
        List<FieldNestedDTO> fields

) {
}
