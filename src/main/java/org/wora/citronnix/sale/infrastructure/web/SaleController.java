package org.wora.citronnix.sale.infrastructure.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.wora.citronnix.sale.application.dto.request.SaleRequestDTO;
import org.wora.citronnix.sale.application.dto.response.SaleResponseDTO;
import org.wora.citronnix.sale.application.service.SaleService;

import java.util.List;

@RestController
@RequestMapping("/sales")
@RequiredArgsConstructor
public class SaleController {
    private final SaleService saleService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SaleResponseDTO getSaleById(@PathVariable Long id) {
        return saleService.findById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<SaleResponseDTO> getAllSales() {
        return saleService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SaleResponseDTO createSale(@Valid @RequestBody SaleRequestDTO requestDTO) {
        return saleService.save(requestDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSale(@PathVariable Long id) {
        saleService.deleteById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SaleResponseDTO updateSale(@PathVariable Long id, @Valid @RequestBody SaleRequestDTO requestDTO) {
        return saleService.update(id, requestDTO);
    }
}
