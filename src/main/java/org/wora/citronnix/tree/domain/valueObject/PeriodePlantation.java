package org.wora.citronnix.tree.domain.valueObject;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Month;

@Embeddable
@Getter
@NoArgsConstructor(force = true)
public class PeriodePlantation {
    @NotNull
    private final LocalDate date;

    @NotNull
    private final LocalDate dateDerniereRecolte;

    public PeriodePlantation(LocalDate date) {
        validatePeriodePlantation(date);
        this.date = date;
        this.dateDerniereRecolte = null;
    }

    public PeriodePlantation(LocalDate date, LocalDate dateDerniereRecolte) {
        validatePeriodePlantation(date);
        validateDerniereRecolte(date, dateDerniereRecolte);
        this.date = date;
        this.dateDerniereRecolte = dateDerniereRecolte;
    }

    private void validatePeriodePlantation(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("La date de plantation ne peut pas être nulle");
        }

        Month month = date.getMonth();
        if (month.compareTo(Month.MARCH) < 0 || month.compareTo(Month.MAY) > 0) {
            throw new IllegalArgumentException("La plantation n'est autorisée qu'entre mars et mai");
        }
    }

    private void validateDerniereRecolte(LocalDate datePlantation, LocalDate dateDerniereRecolte) {
        if (dateDerniereRecolte != null && dateDerniereRecolte.getYear() == datePlantation.getYear()) {
            throw new IllegalArgumentException("Un arbre ne peut pas être récolté deux fois dans la même saison");
        }
    }

    public int getAge() {
        return LocalDate.now().getYear() - date.getYear();
    }

    public int getYear() {
        return date.getYear();
    }
}