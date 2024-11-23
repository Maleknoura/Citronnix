package org.wora.citronnix.tree.application.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.wora.citronnix.farm.domain.entity.Field;
import org.wora.citronnix.farm.domain.repository.FieldRepository;
import org.wora.citronnix.tree.application.dto.request.TreeRequestDTO;
import org.wora.citronnix.tree.application.dto.response.TreeResponseDTO;
import org.wora.citronnix.tree.application.mapper.TreeMapper;
import org.wora.citronnix.tree.application.service.TreeService;
import org.wora.citronnix.tree.domain.entity.Tree;
import org.wora.citronnix.tree.domain.repository.TreeRepository;
import org.wora.citronnix.tree.domain.valueObject.DensiteArbre;
import org.wora.citronnix.tree.domain.valueObject.PeriodePlantation;
import org.wora.citronnix.tree.domain.valueObject.TreeProductivity;

import java.util.List;
@Service
@RequiredArgsConstructor
public class TreeServiceImpl implements TreeService {
    private final FieldRepository fieldRepository;
    private final TreeRepository treeRepository;
    private final TreeMapper treeMapper;

    @Override
    public TreeResponseDTO findById(Long id) {
        Tree tree = treeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Arbre non trouvé"));
        return treeMapper.toResponseDTO(tree);
    }

    @Override
    public List<TreeResponseDTO> findAll() {
        return treeRepository.findAll().stream()
                .map(treeMapper::toResponseDTO)
                .toList();
    }


    @Override
    @Transactional
    public TreeResponseDTO save(TreeRequestDTO treeRequestDTO) {
        Field field = fieldRepository.findById(treeRequestDTO.fieldId())
                .orElseThrow(() -> new EntityNotFoundException("Champ non trouvé"));

        PeriodePlantation periodePlantation = new PeriodePlantation(treeRequestDTO.plantationPeriod());

        TreeProductivity productivity = TreeProductivity.calculerProductivite(periodePlantation.getAge());

        if (field.getTrees().size() >= field.getSuperficie().getValeurEnHectares() * 100) {
            throw new IllegalStateException("La densité d'arbres dépasse 100 arbres par hectare");
        }

        Tree tree = new Tree();
        tree.setField(field);
        tree.setPlantationPeriod(periodePlantation);
        tree.setProductivity(productivity);

        Tree savedTree = treeRepository.save(tree);

        return treeMapper.toResponseDTO(savedTree);
    }


    @Override
    public void deleteById(Long id) {
        if (treeRepository.existsById(id)) {
            treeRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Champ non trouvé avec l'id : " + id);
        }
    }
    @Override
    @Transactional
    public TreeResponseDTO update(Long id, TreeRequestDTO treeRequestDTO) {
        Tree tree = treeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Arbre non trouvé"));

        Field field = fieldRepository.findById(treeRequestDTO.fieldId())
                .orElseThrow(() -> new EntityNotFoundException("Champ non trouvé"));

        PeriodePlantation periodePlantation = new PeriodePlantation(treeRequestDTO.plantationPeriod());
        TreeProductivity productivity = TreeProductivity.calculerProductivite(periodePlantation.getAge());

        if (field.getTrees().size() >= field.getSuperficie().getValeurEnHectares() * 100) {
            throw new IllegalStateException("La densité d'arbres dépasse 100 arbres par hectare");
        }

        tree.setField(field);
        tree.setPlantationPeriod(periodePlantation);
        tree.setProductivity(productivity);

        Tree updatedTree = treeRepository.save(tree);

        return treeMapper.toResponseDTO(updatedTree);
    }

}
