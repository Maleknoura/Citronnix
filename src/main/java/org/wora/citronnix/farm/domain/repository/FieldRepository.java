package org.wora.citronnix.farm.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wora.citronnix.farm.domain.entity.Farm;
import org.wora.citronnix.farm.domain.entity.Field;

import java.util.List;

public interface FieldRepository extends JpaRepository<Field,Long> {
        List<Field> findByFarm(Farm farm);

        long countByFarm(Farm farm);
}
