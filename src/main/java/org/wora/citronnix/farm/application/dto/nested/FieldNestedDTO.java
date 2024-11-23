package org.wora.citronnix.farm.application.dto.nested;


import org.wora.citronnix.farm.domain.valueObject.Superficie;

public record FieldNestedDTO(
        long id,
        Superficie superficie
) {
}
