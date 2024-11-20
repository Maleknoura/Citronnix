package org.wora.citronnix.tree.domain.valueObject;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.wora.citronnix.tree.domain.enums.CategorieAge;

@Embeddable
@Getter
@NoArgsConstructor(force = true)
public class TreeProductivity {
    @Min(value = 0, message = "La quantité par saison ne peut pas être négative")
    @Max(value = 20, message = "La quantité maximale par saison est de 20 kg")
    private final double quantiteParSaison;

    private final CategorieAge categorieAge;

    private TreeProductivity(double quantiteParSaison, CategorieAge categorieAge) {
        this.quantiteParSaison = quantiteParSaison;
        this.categorieAge = categorieAge;
    }

    public static TreeProductivity calculerProductivite(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("L'âge de l'arbre ne peut pas être négatif");
        }

        if (age < 3) {
            return new TreeProductivity(2.5, CategorieAge.JEUNE);
        } else if (age <= 10) {
            return new TreeProductivity(12.0, CategorieAge.MATURE);
        } else if (age <= 20) {
            return new TreeProductivity(20.0, CategorieAge.VIEUX);
        }

        return new TreeProductivity(0.0, CategorieAge.NON_PRODUCTIF);
    }

    public boolean estProductif() {
        return categorieAge != CategorieAge.NON_PRODUCTIF;
    }
}