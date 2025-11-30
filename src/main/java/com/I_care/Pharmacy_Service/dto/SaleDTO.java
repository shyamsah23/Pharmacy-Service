package com.I_care.Pharmacy_Service.dto;

import com.I_care.Pharmacy_Service.entity.Sale;

import java.time.LocalDateTime;

public class SaleDTO {
    private Long id;
    private Long prescriptionId;
    private LocalDateTime saleDate;
    private Double totalAmount;

    public SaleDTO() {
    }

    public SaleDTO(Long id, Long prescriptionId, LocalDateTime saleDate, Double totalAmount) {
        this.id = id;
        this.prescriptionId = prescriptionId;
        this.saleDate = saleDate;
        this.totalAmount = totalAmount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(Long prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public LocalDateTime getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDateTime saleDate) {
        this.saleDate = saleDate;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Sale toEntity() {
        return new Sale(id, prescriptionId, saleDate, totalAmount);
    }
}
