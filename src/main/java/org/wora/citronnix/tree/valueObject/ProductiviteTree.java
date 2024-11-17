package org.wora.citronnix.tree.valueObject;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.wora.citronnix.tree.enums.CategorieAge;

@Embeddable
@Getter
@NoArgsConstructor(force = true)
public class ProductiviteTree{
    private final double quantiteParSaison;
    private final CategorieAge categorieAge;

    private ProductiviteTree(double quantiteParSaison, CategorieAge categorieAge) {
        this.quantiteParSaison = quantiteParSaison;
        this.categorieAge = categorieAge;
    }

    public static ProductiviteTree calculerProductivite(int age) {
        if (age < 3) {
            return new ProductiviteTree(2.5, CategorieAge.JEUNE);
        } else if (age <= 10) {
            return new ProductiviteTree(12.0, CategorieAge.MATURE);
        } else if (age <= 20) {
            return new ProductiviteTree(20.0, CategorieAge.VIEUX);
        }
        return new ProductiviteTree(0.0, CategorieAge.NON_PRODUCTIF);
    }
}
