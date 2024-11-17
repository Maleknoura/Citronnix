package org.wora.citronnix.infrastructure.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wora.citronnix.farm.application.dto.request.FarmRequestDTO;
import org.wora.citronnix.farm.application.dto.response.FarmResponseDTO;
import org.wora.citronnix.farm.application.sevice.impl.FarmServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/farms")
@RequiredArgsConstructor
public class FarmController {
    private final FarmServiceImpl farmService;

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
}
