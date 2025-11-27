package com.I_care.Pharmacy_Service.service;

import com.I_care.Pharmacy_Service.dto.SaleDTO;
import com.I_care.Pharmacy_Service.entity.Sale;
import com.I_care.Pharmacy_Service.exception.PharmacyException;
import com.I_care.Pharmacy_Service.mapper.SaleMapper;
import com.I_care.Pharmacy_Service.utility.PharmacyConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaleServiceImpl implements SaleService {

    @Autowired
    private SaleMapper saleMapper;

    Logger logger = LoggerFactory.getLogger(SaleServiceImpl.class);

    @Override
    public Long createSale(SaleDTO saleDTO) throws PharmacyException {
        logger.info("Sales order being created with details={}", saleDTO);
        if (saleMapper.existsByPrescriptionId(saleDTO.getPrescriptionId())) {
            throw new PharmacyException(PharmacyConstant.SALES_ORDER_ALREADY_EXISTS);
        }
        saleMapper.addSale(saleDTO.getPrescriptionId(), saleDTO.getSaleDate(), saleDTO.getTotalAmount());
        logger.info("Sales order created successfully");
        return saleMapper.getSaleByPrescriptionId(saleDTO.getPrescriptionId()).getId();
    }

    @Override
    public void updateSale(SaleDTO saleDTO) throws PharmacyException {
        logger.info("Sales order being updated with details={}", saleDTO);
        if (!saleMapper.existsByPrescriptionId(saleDTO.getPrescriptionId())) {
            logger.info("Sales order does not exist");
            throw new PharmacyException(PharmacyConstant.SALES_ORDER_DOES_NOT_EXISTS);
        }
        Sale sale = saleMapper.findByPrescriptionId(saleDTO.getPrescriptionId());
        sale.setSaleDate(saleDTO.getSaleDate());
        sale.setTotalAmount(saleDTO.getTotalAmount());
        saleMapper.updateSale(sale.getPrescriptionId(), sale.getSaleDate(), sale.getTotalAmount());
        logger.info("Sales order updated successfully");
    }

    @Override
    public SaleDTO getSale(Long id) throws PharmacyException {
        logger.info("Fetching sales order with id={}", id);
        SaleDTO saleDTO = saleMapper.getSaleById(id).toDTO();
        if (saleDTO == null) {
            logger.info("Sales order does not exist");
            throw new PharmacyException(PharmacyConstant.SALES_ORDER_DOES_NOT_EXISTS);
        }
        return saleDTO;
    }

    @Override
    public SaleDTO getSaleByPrescriptionId(Long prescriptionId) throws PharmacyException {
        logger.info("Fetching sales order with prescriptionId={}", prescriptionId);
        SaleDTO saleDTO = saleMapper.getSaleByPrescriptionId(prescriptionId).toDTO();
        if (saleDTO == null) {
            logger.info("Sales order does not exist");
            throw new PharmacyException(PharmacyConstant.SALES_ORDER_DOES_NOT_EXISTS);
        }
        return saleDTO;
    }
}
