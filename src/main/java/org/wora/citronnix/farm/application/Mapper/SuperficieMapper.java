package org.wora.citronnix.farm.application.Mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import org.wora.citronnix.farm.domain.valueObject.Superficie;

@Mapper(componentModel = "spring")
public interface SuperficieMapper {

    default Double toDouble(Double value) {
        return value != null ? value : 0.0;
    }

    default Superficie toSuperficie(Double superficie) {
        return new Superficie(toDouble(superficie));
    }

    default Double fromSuperficie(Superficie superficie) {
        return superficie != null ? superficie.getValeurEnHectares() : null;
    }
}



