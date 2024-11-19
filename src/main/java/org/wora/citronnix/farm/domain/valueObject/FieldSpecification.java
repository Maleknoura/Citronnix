package org.wora.citronnix.farm.domain.valueObject;


import lombok.Getter;
import lombok.NoArgsConstructor;
import org.wora.citronnix.farm.domain.entity.Farm;

@Getter
@NoArgsConstructor(force = true)
public class FieldSpecification {
    private final Superficie superficie;
    private final Farm farm;

    public FieldSpecification(Superficie superficie, Farm farm) {
        this.superficie = superficie;
        this.farm = farm;

        validateNumberOfFields(farm);
        validateTotalFieldsSuperficie(superficie, farm);
        validateMaxFieldSuperficie(superficie, farm);
    }

    private void validateNumberOfFields(Farm farm) {
        if (farm.getFields().size() >= 10) {
            throw new IllegalStateException("Une ferme ne peut contenir plus de 10 champs");
        }
    }

    private void validateTotalFieldsSuperficie(Superficie newSuperficie, Farm farm) {
        double totalFieldsSuperficie = farm.getFields().stream()
                .mapToDouble(field -> field.getSuperficie().getValeurEnHectares())
                .sum() + newSuperficie.getValeurEnHectares();

        if (totalFieldsSuperficie > farm.getSuperficie()) {
            throw new IllegalArgumentException("La somme des superficies des champs dépasse la superficie de la ferme");
        }
    }

    private void validateMaxFieldSuperficie(Superficie newSuperficie, Farm farm) {
        newSuperficie.validateMaxSuperficie(farm.getSuperficie());
    }
}
