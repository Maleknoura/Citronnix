package org.wora.citronnix.harvest.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import org.wora.citronnix.harvest.domain.enums.Season;
import org.wora.citronnix.sale.domain.entity.Sale;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Harvest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDate dateRecolte;

    @Enumerated(EnumType.STRING)
    private Season saison;

    @Positive
    private Double quantiteTotale;

    @OneToMany(mappedBy = "harvest", cascade = CascadeType.ALL)
    private Set<DetailHarvest> detailsHarvest = new HashSet<>();

    @OneToMany(mappedBy = "harvest", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Sale> sales = new HashSet<>();

    public Season getSeason() {
        return saison;
    }
}
