package org.wora.citronnix.tree.domain.valueObject;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.wora.citronnix.tree.domain.enums.CategorieAge;

@Embeddable
@Getter
@NoArgsConstructor(force = true)
public class TreeProductivity {
    private final double quantiteParSaison;
    private final CategorieAge categorieAge;

    private TreeProductivity(double quantiteParSaison, CategorieAge categorieAge) {
        this.quantiteParSaison = quantiteParSaison;
        this.categorieAge = categorieAge;
    }

    public static TreeProductivity calculerProductivite(int age) {
        if (age < 3) {
            return new TreeProductivity(2.5, CategorieAge.JEUNE);
        } else if (age <= 10) {
            return new TreeProductivity(12.0, CategorieAge.MATURE);
        } else if (age <= 20) {
            return new TreeProductivity(20.0, CategorieAge.VIEUX);
        }
        return new TreeProductivity(0.0, CategorieAge.NON_PRODUCTIF);
    }
}
