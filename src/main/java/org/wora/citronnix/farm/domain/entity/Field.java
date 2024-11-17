package org.wora.citronnix.farm.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.wora.citronnix.farm.domain.valueObject.Superficie;
import org.wora.citronnix.harvest.domain.entity.DetailHarvest;
import org.wora.citronnix.tree.domain.entity.Tree;

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
    @JoinColumn(name = "farm_id", nullable = false)
    private Farm farm;

    @Embedded
    private Superficie superficie;

    @OneToMany(mappedBy = "field", cascade = CascadeType.ALL)
    private Set<Tree> Trees = new HashSet<>();

    @OneToMany(mappedBy = "field")
    private Set<DetailHarvest> harvest = new HashSet<>();
}

