//package org.wora.citronnix.harvest.domain.valueObject;
//
//import jakarta.persistence.Embeddable;
//import org.wora.citronnix.farm.domain.entity.Field;
//import org.wora.citronnix.harvest.domain.entity.DetailHarvest;
//import org.wora.citronnix.harvest.domain.entity.Harvest;
//import org.wora.citronnix.harvest.domain.repository.HarvestRepository;
//import org.wora.citronnix.tree.domain.repository.TreeRepository;
//
//public class HarvestSpecification {
//    private final Harvest harvest;
//    private final Field field;
//    private final TreeRepository treeRepository;
//    private final HarvestRepository harvestRepository;
//
//    public HarvestSpecification(Harvest harvest, Field field, TreeRepository treeRepository, HarvestRepository harvestRepository) {
//        this.harvest = harvest;
//        this.field = field;
//        this.treeRepository = treeRepository;
//        this.harvestRepository = harvestRepository;
//
//        validateSeasonalHarvestLimit();
//        validateTreeHarvestUniqueness();
//    }
//
//
//    private void validateSeasonalHarvestLimit() {
//        long existingHarvestsInSeason = harvestRepository.countByFieldAndSeason(field, harvest.getSaison());
//        if (existingHarvestsInSeason > 0) {
//            throw new IllegalStateException("Un seul récolte par saison est autorisée pour ce champ");
//        }
//    }
//
//    private void validateTreeHarvestUniqueness() {
//        for (DetailHarvest detail : harvest.getDetailsHarvest()) {
//            long treeHarvestCount = harvestRepository.countTreeHarvestInSeason(
//                    detail.getTree(),
//                    harvest.getSaison()
//            );
//            if (treeHarvestCount > 0) {
//                throw new IllegalStateException("Un arbre ne peut être récolté qu'une fois par saison");
//            }
//        }
//
//    }
//}