package org.wora.citronnix.sale.application.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wora.citronnix.harvest.domain.entity.Harvest;
import org.wora.citronnix.harvest.domain.repository.HarvestRepository;
import org.wora.citronnix.sale.application.dto.request.SaleRequestDTO;
import org.wora.citronnix.sale.application.dto.response.SaleResponseDTO;
import org.wora.citronnix.sale.application.mapper.SaleMapper;
import org.wora.citronnix.sale.application.service.SaleService;
import org.wora.citronnix.sale.domain.entity.Sale;
import org.wora.citronnix.sale.domain.repository.SaleRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {
    private final SaleRepository saleRepository;
    private final HarvestRepository harvestRepository;
    private final SaleMapper saleMapper;

    @Override
    public SaleResponseDTO findById(Long id) {
        Sale sale = saleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sale not found"));

        return saleMapper.toSaleResponseDTO(sale);
    }

    @Override
    public List<SaleResponseDTO> findAll() {
        List<Sale> sales = saleRepository.findAll();

        return sales.stream()
                .map(saleMapper::toSaleResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public SaleResponseDTO save(SaleRequestDTO saleRequestDTO) {
        Harvest harvest = harvestRepository.findById(saleRequestDTO.harvestId())
                .orElseThrow(() -> new EntityNotFoundException("Harvest not found"));

        Sale sale = saleMapper.toSale(saleRequestDTO);
        sale.setHarvest(harvest);

        Sale savedSale = saleRepository.save(sale);

        return saleMapper.toSaleResponseDTO(savedSale);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if (!saleRepository.existsById(id)) {
            throw new EntityNotFoundException("Sale not found");
        }
        saleRepository.deleteById(id);
    }

    @Override
    @Transactional
    public SaleResponseDTO update(Long id, SaleRequestDTO saleRequestDTO) {
        Sale existingSale = saleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sale not found"));

        Harvest harvest = harvestRepository.findById(saleRequestDTO.harvestId())
                .orElseThrow(() -> new EntityNotFoundException("Harvest not found"));

        existingSale.setDateVente(saleRequestDTO.dateVente());
        existingSale.setPrixUnitaire(saleRequestDTO.prixUnitaire());
        existingSale.setClient(saleRequestDTO.client());
        existingSale.setHarvest(harvest);
        existingSale.setQuantite(saleRequestDTO.quantite());

        Sale updatedSale = saleRepository.save(existingSale);

        return saleMapper.toSaleResponseDTO(updatedSale);
    }
}
