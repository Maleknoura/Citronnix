package org.wora.citronnix.tree.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.wora.citronnix.field.entity.Field;

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
    public double getProductiviteAnnuelle() {
        int age = getAge();
        if (age < 3) return 2.5;
        if (age <= 10) return 12.0;
        if (age <= 20) return 20.0;
        return 0.0;
    }
}
