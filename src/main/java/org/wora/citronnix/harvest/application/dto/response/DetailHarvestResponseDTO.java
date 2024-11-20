package org.wora.citronnix.harvest.application.dto.response;

public record DetailHarvestResponseDTO(
        Long harvestId,
        Long treeId,
        Double quantite
) {
}
