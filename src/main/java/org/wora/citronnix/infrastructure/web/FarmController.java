package org.wora.citronnix.infrastructure.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
