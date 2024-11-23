package org.wora.citronnix.tree.domain.valueObject;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(force = true)
public class DensiteArbre {
    @Min(value = 0, message = "Le nombre d'arbres ne peut pas être négatif")
    @Max(value = 100, message = "La densité maximale est de 100 arbres par hectare")
    private final int nombreArbres;

    @Min(value = 0, message = "La surface ne peut pas être négative")
    private final double surfaceEnHectares;

    public DensiteArbre(int nombreArbres, double surfaceEnHectares) {
        validateDensite(nombreArbres, surfaceEnHectares);
        this.nombreArbres = nombreArbres;
        this.surfaceEnHectares = surfaceEnHectares;
    }

    private void validateDensite(int nombreArbres, double surfaceEnHectares) {
        if (surfaceEnHectares <= 0) {
            throw new IllegalArgumentException("La surface doit être supérieure à zéro");
        }

        double densiteMaximale = 100 * surfaceEnHectares;

        if (nombreArbres > densiteMaximale) {
            throw new IllegalArgumentException("La densité maximale est de " + densiteMaximale + " arbres");
        }
    }

    public double getDensiteParHectare() {
        return nombreArbres / surfaceEnHectares;
    }
}
