package org.wora.citronnix.farm.domain.valueObject;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(force = true)
public class Superficie {
    private final Double valeurEnHectares;

    public Superficie(Double valeurEnHectares) {
        if (valeurEnHectares < 0.1) {
            throw new IllegalArgumentException("La superficie minimale d'un champ doit être de 0.1 hectare");
        }
        this.valeurEnHectares = valeurEnHectares;
    }


    public void validateMaxSuperficie(Double farmSuperficie) {
        if (valeurEnHectares > (farmSuperficie * 0.5)) {
            throw new IllegalArgumentException("La superficie du champ ne peut pas dépasser 50% de la superficie de la ferme");
        }
    }
}