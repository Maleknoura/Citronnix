package org.wora.citronnix.harvest.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.wora.citronnix.farm.domain.entity.Field;
import org.wora.citronnix.harvest.domain.embeddable.HarvestDetailId;
import org.wora.citronnix.tree.domain.entity.Tree;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


@Table(name = "detail_harvest")
public class DetailHarvest {
    @EmbeddedId
    private HarvestDetailId id;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("harvestId")
    @JoinColumn(name = "harvest_id")
    private Harvest harvest;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("treeId")
    @JoinColumn(name = "tree_id")
    private Tree tree;

    @Column(nullable = false)
    private Double quantite;


}

