package org.wora.citronnix.harvest.domain.valueObject;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.wora.citronnix.farm.domain.entity.Field;
import org.wora.citronnix.harvest.domain.entity.DetailHarvest;
import org.wora.citronnix.harvest.domain.entity.Harvest;
import org.wora.citronnix.harvest.domain.enums.Season;
import org.wora.citronnix.harvest.domain.repository.HarvestRepository;
import org.wora.citronnix.tree.domain.entity.Tree;
import org.wora.citronnix.tree.domain.repository.TreeRepository;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Getter
@EqualsAndHashCode
public class HarvestDetails {
    private static final int MAX_TREE_AGE = 20;

    private final Field field;
    private final Season season;
    private final Set<Tree> trees;
    private final Harvest harvest;

    public HarvestDetails(Field field, Season season, Set<Tree> trees, Harvest harvest) {
        validateConstructorParameters(field, season, trees, harvest);

        this.field = field;
        this.season = season;
        this.trees = Collections.unmodifiableSet(validateTrees(new HashSet<>(trees)));
        this.harvest = harvest;
    }

    private void validateConstructorParameters(Field field, Season season, Set<Tree> trees, Harvest harvest) {
        if (field == null) {
            throw new IllegalArgumentException("Field must not be null");
        }
        if (season == null) {
            throw new IllegalArgumentException("Season must not be null");
        }
        if (trees == null || trees.isEmpty()) {
            throw new IllegalArgumentException("Trees set must not be null or empty");
        }
        if (harvest == null) {
            throw new IllegalArgumentException("Harvest must not be null");
        }
    }

    private Set<Tree> validateTrees(Set<Tree> treesToValidate) {
        for (Tree tree : treesToValidate) {
            validateTree(tree);
        }
        return treesToValidate;
    }

    private void validateTree(Tree tree) {
        if (tree == null) {
            throw new IllegalArgumentException("Tree cannot be null");
        }
        if (tree.getAge() > MAX_TREE_AGE) {
            throw new IllegalStateException(
                    String.format("Tree with ID %d is too old to be productive (age: %d)",
                            tree.getId(), tree.getAge())
            );
        }
        if (tree.hasBeenHarvestedInSeason(season)) {
            throw new IllegalStateException(
                    String.format("Tree with ID %d has already been harvested this season",
                            tree.getId())
            );
        }
    }

    public void addToHarvest() {
        trees.forEach(tree -> {
            DetailHarvest detail = new DetailHarvest();
            detail.setTree(tree);
            detail.setHarvest(this.harvest);
            tree.addHarvestDetail(detail);
        });
    }
}