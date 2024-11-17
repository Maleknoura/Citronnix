package org.wora.citronnix.tree.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.wora.citronnix.farm.entity.Field;
import org.wora.citronnix.tree.valueObject.ProductiviteTree;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Tree {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "champ_id", nullable = false)
    private Field field;

    @NotNull
    private LocalDate datePlantation;

    @Transient
    public int getAge() {
        return LocalDate.now().getYear() - datePlantation.getYear();
    }

    @Transient
    public ProductiviteTree getProductivite() {
        return ProductiviteTree.calculerProductivite(getAge());
    }
}
