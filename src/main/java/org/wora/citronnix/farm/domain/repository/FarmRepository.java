package org.wora.citronnix.farm.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.wora.citronnix.farm.application.dto.request.FarmSearchCriteria;
import org.wora.citronnix.farm.domain.entity.Farm;

import java.util.List;

public interface FarmRepository extends JpaRepository<Farm,Long>, JpaSpecificationExecutor<Farm> {
    @Query("SELECT COUNT(f) > 0 FROM Farm f WHERE f.nom = :nom")
    boolean existsByNom(String nom);
}
