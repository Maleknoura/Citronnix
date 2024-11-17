package org.wora.citronnix.tree.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.wora.citronnix.farm.domain.entity.Field;
import org.wora.citronnix.tree.domain.valueObject.PeriodePlantation;
import org.wora.citronnix.tree.domain.valueObject.TreeProductivity;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Tree {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "field_id", nullable = false)
    private Field field;

    @Embedded
    private PeriodePlantation datePlantation;
    @Embedded
    private TreeProductivity productivity;
    @Transient
    public int getAge() {
        return LocalDate.now().getYear() - datePlantation.getYear();
    }

    @Transient
    public TreeProductivity getProductivite() {
        return TreeProductivity.calculerProductivite(getAge());
    }
}
