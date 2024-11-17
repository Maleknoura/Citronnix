package org.wora.citronnix.farm.valueObject;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.wora.citronnix.farm.enums.UniteSuperficie;

@Embeddable
@NoArgsConstructor(force = true)
public class Superficie {
    @Positive
    private final Double valeur;
    private final UniteSuperficie unite;

    public Superficie(Double valeur, UniteSuperficie unite) {
        if (valeur == null || valeur <= 0) {
            throw new IllegalArgumentException("La superficie doit être positive");
        }
        this.valeur = valeur;
        this.unite = unite;
    }

    public Double enHectares() {
        return unite == UniteSuperficie.HECTARE ? valeur : valeur / 10000;
    }

    public Double enMetresCarres() {
        return unite == UniteSuperficie.METRE_CARRE ? valeur : valeur * 10000;
    }
}