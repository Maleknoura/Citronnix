package org.wora.citronnix.harvest.domain.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@ToString
public class HarvestDetailId implements Serializable {
    @Getter
    @Column(name = "harvest_id")
    private Long harvestId;

    @Getter
    @Column(name = "tree_id")
    private Long treeId;

    public HarvestDetailId(Long harvestId, Long treeId) {
        this.harvestId = harvestId;
        this.treeId = treeId;
    }


}
