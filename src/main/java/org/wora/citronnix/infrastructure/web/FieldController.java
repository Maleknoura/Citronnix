package org.wora.citronnix.infrastructure.web;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
        FieldResponseDTO createdField = fieldServiceImpl.save(fieldRequestDTO);
        return ResponseEntity.ok(createdField);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>delete(@PathVariable long id){
        fieldServiceImpl.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
