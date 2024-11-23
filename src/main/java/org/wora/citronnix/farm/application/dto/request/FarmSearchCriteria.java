package org.wora.citronnix.farm.application.dto.request;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record FarmSearchCriteria(
        String nom,
        String localisation,
        Double minSuperficie,
        Double maxSuperficie,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        LocalDate startDate,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        LocalDate endDate
) {
}
