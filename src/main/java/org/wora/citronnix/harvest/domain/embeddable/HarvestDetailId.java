package org.wora.citronnix.harvest.domain.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
public class HarvestDetailId implements Serializable {
    @Column(name = "harvest_id")
    private Long harvestId;

    @Column(name = "tree_id")
    private Long treeId;

    public HarvestDetailId(Long harvestId, Long treeId) {
        this.harvestId = harvestId;
        this.treeId = treeId;
    }


}
