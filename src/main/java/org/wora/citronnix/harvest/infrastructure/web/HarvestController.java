package org.wora.citronnix.harvest.infrastructure.web;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wora.citronnix.harvest.application.dto.request.HarvestRequestDTO;
import org.wora.citronnix.harvest.application.dto.response.HarvestResponseDTO;
import org.wora.citronnix.harvest.application.service.HarvestService;

import java.util.List;

@RestController
@RequestMapping("/api/harvests")
public class HarvestController {

    private final HarvestService harvestService;

    @Autowired
    public HarvestController(HarvestService harvestService) {
        this.harvestService = harvestService;
    }

    @PostMapping
    public ResponseEntity<HarvestResponseDTO> createHarvest(
            @Valid @RequestBody HarvestRequestDTO requestDTO
    ) {
        HarvestResponseDTO savedHarvest = harvestService.save(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedHarvest);
    }
    @GetMapping("/{id}")
    public ResponseEntity<HarvestResponseDTO> getHarvestById(@PathVariable Long id) {
        HarvestResponseDTO harvest = harvestService.findById(id);
        return ResponseEntity.ok(harvest);
    }
    @GetMapping
    public ResponseEntity<List<HarvestResponseDTO>> getAllHarvests() {
        List<HarvestResponseDTO> harvests = harvestService.findAll();
        return ResponseEntity.ok(harvests);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHarvest(@PathVariable Long id) {
        harvestService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<HarvestResponseDTO> updateHarvest(
            @PathVariable Long id,
            @RequestBody HarvestRequestDTO requestDTO) {
        HarvestResponseDTO updatedHarvest = harvestService.update(id, requestDTO);
        return ResponseEntity.ok(updatedHarvest);
    }
}
