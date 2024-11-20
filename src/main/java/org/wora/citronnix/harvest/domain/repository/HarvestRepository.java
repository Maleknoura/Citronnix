package org.wora.citronnix.harvest.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.wora.citronnix.farm.domain.entity.Field;
import org.wora.citronnix.harvest.domain.entity.Harvest;
import org.wora.citronnix.harvest.domain.enums.Season;
import org.wora.citronnix.tree.domain.entity.Tree;
@Repository
public interface HarvestRepository extends JpaRepository<Harvest, Long> {

//    @Query("SELECT COUNT(d) FROM DetailHarvest d WHERE d.tree = :tree AND d.harvest.saison = :saison")
//    long countTreeHarvestInSeason(Tree tree, Season saison);
//
//    long countByFieldAndSeason(Field field, Season saison);
}
