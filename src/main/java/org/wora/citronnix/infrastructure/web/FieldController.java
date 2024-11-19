package org.wora.citronnix.infrastructure.web;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wora.citronnix.farm.application.dto.request.FieldRequestDTO;
import org.wora.citronnix.farm.application.dto.response.FieldResponseDTO;
import org.wora.citronnix.farm.application.sevice.FieldService;
import org.wora.citronnix.farm.application.sevice.impl.FieldServiceImpl;

@RestController
@RequestMapping("/api/fields")
@RequiredArgsConstructor
public class FieldController {

    private final FieldServiceImpl fieldServiceImpl;

    @PostMapping
    public ResponseEntity<FieldResponseDTO> createField(@RequestBody FieldRequestDTO fieldRequestDTO) {
        FieldResponseDTO createdField = fieldServiceImpl.createField(fieldRequestDTO);
        return ResponseEntity.ok(createdField);
    }


}
