package org.wora.citronnix.tree.infrastructure.web;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wora.citronnix.tree.application.dto.request.TreeRequestDTO;
import org.wora.citronnix.tree.application.dto.response.TreeResponseDTO;
import org.wora.citronnix.tree.application.service.TreeService;
import org.wora.citronnix.tree.application.service.impl.TreeServiceImpl;

@RestController
@RequestMapping("/api/trees")
@RequiredArgsConstructor
public class TreeController {

    private final TreeServiceImpl treeService;


    @PostMapping()
    public ResponseEntity<?> createTree(@RequestBody TreeRequestDTO treeRequestDto) {
        try {
            TreeResponseDTO response = treeService.save(treeRequestDto);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.out.println("Error while creating tree: "+ e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }






}
