package com.I_care.Pharmacy_Service.service;

import com.I_care.Pharmacy_Service.dto.SaleDTO;
import com.I_care.Pharmacy_Service.exception.PharmacyException;

public interface SaleService {
    Long createSale(SaleDTO saleDTO) throws PharmacyException;

    void updateSale(SaleDTO saleDTO) throws PharmacyException;

    SaleDTO getSale(Long id) throws PharmacyException;

    SaleDTO getSaleByPrescriptionId(Long prescriptionId) throws PharmacyException;
}
