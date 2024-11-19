package org.wora.citronnix.farm.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wora.citronnix.farm.domain.entity.Farm;

public interface FarmRepository extends JpaRepository<Farm,Long> {
    boolean existsByNom(String nom);
}
