package org.wora.citronnix.farm.domain.valueObject;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.wora.citronnix.farm.domain.entity.Farm;
import org.wora.citronnix.farm.domain.entity.Field;
import org.wora.citronnix.farm.domain.repository.FarmRepository;
import org.wora.citronnix.farm.domain.repository.FieldRepository;

import java.util.List;

@Getter
@NoArgsConstructor(force = true)
public class FieldSpecification {
    private final Superficie superficie;
    private final Farm farm;
    private final FieldRepository fieldRepository;

    public FieldSpecification(Superficie superficie, Farm farm, FieldRepository fieldRepository) {
        this.superficie = superficie;
        this.farm = farm;
        this.fieldRepository = fieldRepository;

        validateNumberOfFields(farm);
        validateTotalFieldsSuperficie(superficie, farm);
        superficie.validateMaxSuperficie(farm.getSuperficie());
    }





    private void validateNumberOfFields(Farm farm) {
        if (farm.getFields().size() >= 10) {
            throw new IllegalStateException("Une ferme ne peut contenir plus de 10 champs");
        }
    }

    private void validateTotalFieldsSuperficie(Superficie newSuperficie, Farm farm) {
        List<Field> farmFields = fieldRepository.findByFarm(farm);

        double totalFieldsSuperficie = farmFields.stream()
                .mapToDouble(field -> field.getSuperficie().getValeurEnHectares())
                .sum();

        System.out.println("Champs de la ferme avant ajout: " + farmFields);
        System.out.println("Total superficie des champs avant ajout: " + totalFieldsSuperficie);

        if (totalFieldsSuperficie + newSuperficie.getValeurEnHectares() > farm.getSuperficie()) {
            throw new IllegalArgumentException("La somme des superficies des champs dépasse la superficie de la ferme");
        }
    }



}

