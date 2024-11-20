package org.wora.citronnix.harvest.domain.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class HarvestDetailId implements Serializable {
    @Column(name = "harvest_id")
    private Long harvestId;

    @Column(name = "tree_id")
    private Long treeId;
}
