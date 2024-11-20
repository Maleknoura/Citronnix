package org.wora.citronnix.sale.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wora.citronnix.sale.domain.entity.Sale;

public interface SaleRepository extends JpaRepository<Sale,Long> {
}
