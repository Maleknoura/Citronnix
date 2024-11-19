package org.wora.citronnix.farm.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wora.citronnix.farm.domain.entity.Field;

public interface FieldRepository extends JpaRepository<Field,Long> {
}
