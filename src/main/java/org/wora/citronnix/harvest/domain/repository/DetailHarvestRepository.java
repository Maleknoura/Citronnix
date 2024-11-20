package org.wora.citronnix.harvest.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wora.citronnix.harvest.domain.embeddable.HarvestDetailId;
import org.wora.citronnix.harvest.domain.entity.DetailHarvest;

public interface DetailHarvestRepository extends JpaRepository<DetailHarvest, HarvestDetailId> {
}
