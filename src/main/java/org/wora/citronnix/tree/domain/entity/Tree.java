package org.wora.citronnix.tree.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.wora.citronnix.farm.domain.entity.Field;
import org.wora.citronnix.harvest.domain.entity.DetailHarvest;
import org.wora.citronnix.harvest.domain.enums.Season;
import org.wora.citronnix.tree.domain.valueObject.PeriodePlantation;
import org.wora.citronnix.tree.domain.valueObject.TreeProductivity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Tree {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "field_id", nullable = false)
    private Field field;

    @Embedded
    private PeriodePlantation plantationPeriod;

    @Embedded
    private TreeProductivity productivity;

    @OneToMany(mappedBy = "tree", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DetailHarvest> harvestDetails = new HashSet<>();

    public Tree() {
        this.harvestDetails = new HashSet<>();
    }

    @Transient
    public int getAge() {
        if (plantationPeriod == null) {
            throw new IllegalStateException("Plantation period is not set");
        }
        return LocalDate.now().getYear() - plantationPeriod.getYear();
    }

    @Transient
    public TreeProductivity calculateProductivity() {
        return TreeProductivity.calculerProductivite(getAge());
    }

    public boolean hasBeenHarvestedInSeason(Season season) {
        if (season == null) {
            throw new IllegalArgumentException("Season cannot be null");
        }
        return harvestDetails.stream()
                .anyMatch(detail -> detail.getHarvest().getSeason() == season);
    }

    public void addHarvestDetail(DetailHarvest detail) {
        if (detail == null) {
            throw new IllegalArgumentException("DetailHarvest cannot be null");
        }
        harvestDetails.add(detail);
        detail.setTree(this);
    }
}
