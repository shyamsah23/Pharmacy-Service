package com.I_care.Pharmacy_Service.service;

import com.I_care.Pharmacy_Service.dto.SaleItemDTO;
import com.I_care.Pharmacy_Service.exception.PharmacyException;

import java.util.List;

public interface SaleItemService {
    Long createSaleItem(SaleItemDTO saleItemDTO) throws PharmacyException;

    void createMultipleSaleItem(List<SaleItemDTO> saleItemDTOs) throws PharmacyException;

    void updateSaleItem(SaleItemDTO saleItemDTO) throws PharmacyException;

    List<SaleItemDTO> getSaleItemsBySaleId(Long saleId) throws PharmacyException;

    SaleItemDTO getSaleItem(Long id) throws PharmacyException;
}
