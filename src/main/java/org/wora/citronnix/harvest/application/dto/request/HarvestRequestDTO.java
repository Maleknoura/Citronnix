package org.wora.citronnix.harvest.application.dto.request;

import org.wora.citronnix.harvest.domain.enums.Season;

import java.time.LocalDate;
import java.util.Set;

public record HarvestRequestDTO (
    Long fieldId,
    LocalDate dateRecolte,
    Season saison,
    Double quantiteTotale
    ){}
