package org.wora.citronnix.tree.domain.valueObject;

import jakarta.persistence.Embeddable;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Month;

@Embeddable
@NoArgsConstructor(force = true)
public class PeriodePlantation {
    private final LocalDate date;

    public PeriodePlantation(LocalDate date) {
        if (!estPeriodeValide(date)) {
            throw new IllegalArgumentException("La plantation n'est autorisée qu'entre mars et mai");
        }
        this.date = date;
    }

    private boolean estPeriodeValide(LocalDate date) {
        Month month = date.getMonth();
        return month.compareTo(Month.MARCH) >= 0 && month.compareTo(Month.MAY) <= 0;
    }


    public int getYear() {
        return date.getYear();
    }
}

