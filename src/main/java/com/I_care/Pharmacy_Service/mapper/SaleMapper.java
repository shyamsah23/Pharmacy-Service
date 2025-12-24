package com.I_care.Pharmacy_Service.mapper;

import com.I_care.Pharmacy_Service.entity.Sale;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface SaleMapper {
    Long addSale(Long prescriptionId, LocalDateTime saleDate, Double totalAmount);

    List<Sale> getAllSales();

    boolean existsByPrescriptionId(Long prescriptionId);

    Sale findByPrescriptionId(Long prescriptionId);

    void updateSale(Long prescriptionId, LocalDateTime saleDate, Double totalAmount);

    Sale getSaleById(Long id);

    Sale getSaleByPrescriptionId(Long prescriptionId);
}

