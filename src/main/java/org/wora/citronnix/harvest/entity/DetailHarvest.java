package org.wora.citronnix.harvest.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import org.wora.citronnix.farm.entity.Field;
import org.wora.citronnix.tree.entity.Tree;

@Entity
public class DetailHarvest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "harvest_id", nullable = false)
    private Harvest harvest;

    @ManyToOne
    @JoinColumn(name = "tree_id", nullable = false)
    private Tree tree;

    @ManyToOne
    @JoinColumn(name = "field_id", nullable = false)
    private Field field;

    @Positive
    private Double quantite;
}
