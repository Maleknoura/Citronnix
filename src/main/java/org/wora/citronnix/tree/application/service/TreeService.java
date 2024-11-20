package org.wora.citronnix.tree.application.service;

import org.wora.citronnix.shared.service.GenericService;
import org.wora.citronnix.tree.application.dto.request.TreeRequestDTO;
import org.wora.citronnix.tree.application.dto.response.TreeResponseDTO;
import org.wora.citronnix.tree.domain.entity.Tree;

public interface TreeService extends GenericService<Tree,Long, TreeRequestDTO, TreeResponseDTO> {
}
