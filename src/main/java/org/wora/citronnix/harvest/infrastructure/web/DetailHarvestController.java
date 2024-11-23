package org.wora.citronnix.harvest.infrastructure.web;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wora.citronnix.harvest.application.dto.request.DetailHarvestRequestDTO;
import org.wora.citronnix.harvest.application.dto.response.DetailHarvestResponseDTO;
import org.wora.citronnix.harvest.application.service.DetailHarvestService;
import org.wora.citronnix.harvest.domain.embeddable.HarvestDetailId;

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

    @PutMapping("/{harvestId}/{treeId}")
    @ResponseStatus(HttpStatus.OK)
    public DetailHarvestResponseDTO updateDetailHarvest(
            @PathVariable Long harvestId,
            @PathVariable Long treeId,
            @RequestBody DetailHarvestRequestDTO detailHarvestRequestDTO) {

        HarvestDetailId harvestDetailId = new HarvestDetailId(harvestId, treeId);

        return detailHarvestService.update(harvestDetailId, detailHarvestRequestDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<DetailHarvestResponseDTO> getAllDetailHarvests() {
        return detailHarvestService.findAll();
    }

    @GetMapping("/{harvestId}/{treeId}")
    @ResponseStatus(HttpStatus.OK)
    public DetailHarvestResponseDTO getDetailHarvestById(@PathVariable Long harvestId, @PathVariable Long treeId) {
        HarvestDetailId id = new HarvestDetailId(harvestId, treeId);
        return detailHarvestService.findById(id);
    }

    @DeleteMapping("/{harvestId}/{treeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long harvestId, @PathVariable Long treeId) {
        HarvestDetailId harvestDetailId = new HarvestDetailId(harvestId, treeId);
        detailHarvestService.deleteById(harvestDetailId);
    }


}

