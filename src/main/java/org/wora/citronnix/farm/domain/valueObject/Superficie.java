package org.wora.citronnix.farm.domain.valueObject;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(force = true)
@Getter
public class Superficie {
    private final Double valeurEnHectares;
    private final Double superficieTotaleFerme;

    public Superficie(Double valeurEnHectares, Double superficieTotaleFerme) {
        if (valeurEnHectares < 0.1) {
            throw new IllegalArgumentException("La superficie minimale d'un champ doit être de 0.1 hectare");
        }
        if (valeurEnHectares > (superficieTotaleFerme * 0.5)) {
            throw new IllegalArgumentException("La superficie du champ ne peut pas dépasser 50% de la superficie de la ferme");
        }
        this.valeurEnHectares = valeurEnHectares;
        this.superficieTotaleFerme = superficieTotaleFerme;
    }

    public Double getValeurEnHectares() {
        return valeurEnHectares;
    }

    public Double getValeurEnMetresCarres() {
        return valeurEnHectares * 10000;
    }
}