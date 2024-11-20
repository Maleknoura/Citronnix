package org.wora.citronnix.harvest.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.wora.citronnix.farm.domain.entity.Field;
import org.wora.citronnix.harvest.domain.embeddable.HarvestDetailId;
import org.wora.citronnix.tree.domain.entity.Tree;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetailHarvest {
    @EmbeddedId
    private HarvestDetailId id;

    @MapsId("harvestId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "harvest_id", nullable = false, insertable = false, updatable = false)
    private Harvest harvest;

    @MapsId("treeId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tree_id", nullable = false, insertable = false, updatable = false)
    private Tree tree;

    @Positive
    private Double quantite;


}

