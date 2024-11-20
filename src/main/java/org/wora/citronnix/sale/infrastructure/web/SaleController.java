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


}
