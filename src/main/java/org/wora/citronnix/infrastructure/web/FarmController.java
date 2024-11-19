package org.wora.citronnix.infrastructure.web;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wora.citronnix.farm.application.dto.request.FarmRequestDTO;
import org.wora.citronnix.farm.application.dto.response.FarmResponseDTO;
import org.wora.citronnix.farm.application.sevice.FarmService;
import org.wora.citronnix.farm.application.sevice.impl.FarmServiceImpl;
import org.wora.citronnix.farm.domain.repository.FarmRepository;

import java.util.List;

@RestController
@RequestMapping("/farms")
public class FarmController {
    private final FarmServiceImpl farmService;
    private final FarmRepository farmRepository;

    @Autowired
    public FarmController(FarmServiceImpl farmService, FarmRepository farmRepository) {
        this.farmService = farmService;
        this.farmRepository = farmRepository;
    }

    @GetMapping
    public ResponseEntity<List<FarmResponseDTO>> findFarms() {
        List<FarmResponseDTO> farms = farmService.findAll();
        return new ResponseEntity<>(farms, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<FarmResponseDTO>save(@RequestBody @Valid FarmRequestDTO farmRequestDTO){
        FarmResponseDTO savedfarms = farmService.save(farmRequestDTO);
        return new ResponseEntity<>(savedfarms,HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void>delete(@PathVariable long id){
        farmService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("/{id}")
    public ResponseEntity<FarmResponseDTO> updateFarm(
            @PathVariable Long id,
            @RequestBody @Valid FarmRequestDTO farmRequestDTO) {
        FarmResponseDTO updatedFarm = farmService.update(id, farmRequestDTO);
        return ResponseEntity.ok(updatedFarm);
    }
}
