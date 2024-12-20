package org.wora.citronnix.tree.infrastructure.web;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wora.citronnix.tree.application.dto.request.TreeRequestDTO;
import org.wora.citronnix.tree.application.dto.response.TreeResponseDTO;
import org.wora.citronnix.tree.application.service.TreeService;
import org.wora.citronnix.tree.application.service.impl.TreeServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/trees")
@RequiredArgsConstructor
public class TreeController {

    private final TreeService treeService;


    @PostMapping()
    public ResponseEntity<?> createTree(@RequestBody TreeRequestDTO treeRequestDto) {
        TreeResponseDTO response = treeService.save(treeRequestDto);
        return ResponseEntity.ok(response);

    }

    @GetMapping
    public ResponseEntity<List<TreeResponseDTO>> getAllTrees() {
        List<TreeResponseDTO> response = treeService.findAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TreeResponseDTO> getTreeById(@PathVariable Long id) {
        try {
            TreeResponseDTO response = treeService.findById(id);
            return ResponseEntity.ok(response);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        treeService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TreeResponseDTO> updateTree(@PathVariable Long id, @RequestBody TreeRequestDTO treeRequestDto) {
        TreeResponseDTO response = treeService.update(id, treeRequestDto);
        return ResponseEntity.ok(response);
    }

}