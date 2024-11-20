package org.wora.citronnix.harvest.infrastructure.web;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wora.citronnix.harvest.application.dto.request.DetailHarvestRequestDTO;
import org.wora.citronnix.harvest.application.dto.response.DetailHarvestResponseDTO;
import org.wora.citronnix.harvest.application.service.DetailHarvestService;

import java.util.List;

@RestController
@RequestMapping("/api/detail-harvests")
@RequiredArgsConstructor
public class DetailHarvestController {

    private final DetailHarvestService detailHarvestService;


    @PostMapping
    public ResponseEntity<DetailHarvestResponseDTO> saveDetailHarvest(@RequestBody DetailHarvestRequestDTO requestDTO) {
        DetailHarvestResponseDTO responseDTO = detailHarvestService.save(requestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<DetailHarvestResponseDTO> getAllDetailHarvests() {
        return detailHarvestService.findAll();
    }
}

