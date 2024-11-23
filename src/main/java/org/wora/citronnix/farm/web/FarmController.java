package org.wora.citronnix.farm.web;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wora.citronnix.farm.application.dto.request.FarmRequestDTO;
import org.wora.citronnix.farm.application.dto.request.FarmSearchCriteria;
import org.wora.citronnix.farm.application.dto.response.FarmResponseDTO;
import org.wora.citronnix.farm.application.sevice.FarmService;
import org.wora.citronnix.farm.application.sevice.impl.FarmServiceImpl;
import org.wora.citronnix.farm.domain.entity.Farm;
import org.wora.citronnix.farm.domain.repository.FarmRepository;

import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/farms")
public class FarmController {
    private final FarmServiceImpl farmService;
    private final FarmRepository farmRepository;


    @GetMapping
    public ResponseEntity<List<FarmResponseDTO>> findFarms() {
        List<FarmResponseDTO> farms = farmService.findAll();
        return new ResponseEntity<>(farms, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<FarmResponseDTO> findFarmById(@PathVariable long id) {
        FarmResponseDTO farmResponse = farmService.findById(id);
        return new ResponseEntity<>(farmResponse, HttpStatus.OK);
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
    @PostMapping("/search")
    public ResponseEntity<List<FarmResponseDTO>> searchFarms(@Valid @RequestBody FarmSearchCriteria criteria) {
        List<FarmResponseDTO> farms = farmService.searchFarms(criteria);
        return ResponseEntity.ok(farms);
    }

}
