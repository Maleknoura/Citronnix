package org.wora.citronnix.harvest.application.dto.request;

public record DetailHarvestRequestDTO
(   Long harvestId,
    Long treeId,
    Double quantite
){}
