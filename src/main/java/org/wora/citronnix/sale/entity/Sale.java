package org.wora.citronnix.sale.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import org.wora.citronnix.harvest.entity.Harvest;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDate dateVente;

    @Positive
    private BigDecimal prixUnitaire;

    @NotBlank
    private String client;

    @ManyToOne
    @JoinColumn(name = "harvest_id", nullable = false)
    private Harvest recolte;

    @Positive
    private Double quantite;

    @Transient
    public BigDecimal getRevenu() {
        return prixUnitaire.multiply(BigDecimal.valueOf(quantite));
    }
}
