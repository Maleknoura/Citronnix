package org.wora.citronnix.farm.application.dto.response;

import org.wora.citronnix.tree.application.dto.nested.TreeNestedDTO;

import java.util.List;

public record FieldResponseDTO(
        Long id,
        Double superficie,
        Long farmId,
        List<TreeNestedDTO> trees
) {
}
