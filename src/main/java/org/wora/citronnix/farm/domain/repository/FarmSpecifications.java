package org.wora.citronnix.farm.domain.repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.wora.citronnix.farm.application.dto.request.FarmSearchCriteria;
import org.wora.citronnix.farm.domain.entity.Farm;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public interface FarmSpecifications {

    public static Specification<Farm> search(FarmSearchCriteria criteria) {
        return (Root<Farm> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (criteria.nom() != null) {
                predicates.add(cb.like(cb.lower(root.get("nom")), "%" + criteria.nom().toLowerCase() + "%"));
            }
            if (criteria.localisation() != null) {
                predicates.add(cb.like(cb.lower(root.get("localisation")), "%" + criteria.localisation().toLowerCase() + "%"));
            }
            if (criteria.minSuperficie() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("superficie"), criteria.minSuperficie()));
            }
            if (criteria.maxSuperficie() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("superficie"), criteria.maxSuperficie()));
            }
            if (criteria.startDate() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("dateCreation"), criteria.startDate()));
            }
            if (criteria.endDate() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("dateCreation"), criteria.endDate()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
