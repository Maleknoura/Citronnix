package org.wora.citronnix.tree.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.wora.citronnix.tree.domain.entity.Tree;

@Repository
public interface TreeRepository extends JpaRepository<Tree,Long> {
}
