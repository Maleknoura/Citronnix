package org.wora.citronnix.field.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import org.wora.citronnix.farm.entity.Farm;
import org.wora.citronnix.harvest.entity.DetailHarvest;
import org.wora.citronnix.tree.entity.Tree;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Field {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ferme_id", nullable = false)
    private Farm farm;

    @Min(value = 1000)
    private Double superficie;

    @OneToMany(mappedBy = "champ", cascade = CascadeType.ALL)
    private Set<Tree> arbres = new HashSet<>();

    @OneToMany(mappedBy = "champ")
    private Set<DetailHarvest> harvest = new HashSet<>();
}

