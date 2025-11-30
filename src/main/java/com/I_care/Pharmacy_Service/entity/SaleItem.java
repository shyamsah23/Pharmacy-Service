package com.I_care.Pharmacy_Service.entity;

import com.I_care.Pharmacy_Service.dto.SaleItemDTO;
import jakarta.persistence.*;

public class SaleItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sale_id", nullable = false)
    private Sale sale;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medicine_id", nullable = false)
    private Medicine medicine;
    @Column(unique = true)
    private String batchNo;
    private Integer quantity;
    private Double unitPrice;

    public SaleItem() {
    }

    public SaleItem(Long id, Sale sale, Medicine medicine, String batchNo, Integer quantity, Double unitPrice) {
        this.id = id;
        this.sale = sale;
        this.medicine = medicine;
        this.batchNo = batchNo;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public SaleItemDTO toDTO() {
        return new SaleItemDTO(id, sale != null ? sale.getId() : null, medicine != null ? medicine.getId() : null, batchNo, quantity, unitPrice);
    }
}
