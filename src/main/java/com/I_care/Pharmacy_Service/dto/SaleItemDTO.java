package com.I_care.Pharmacy_Service.dto;

import com.I_care.Pharmacy_Service.entity.Medicine;
import com.I_care.Pharmacy_Service.entity.Sale;
import com.I_care.Pharmacy_Service.entity.SaleItem;

public class SaleItemDTO {
    private Long id;
    private Long saleId;
    private Long medicineId;
    private String batchNo;
    private Integer quantity;
    private Double unitPrice;

    public SaleItemDTO() {
    }

    public SaleItemDTO(Long id, Long saleId, Long medicineId, String batchNo, Integer quantity, Double unitPrice) {
        this.id = id;
        this.saleId = saleId;
        this.medicineId = medicineId;
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

    public Long getSaleId() {
        return saleId;
    }

    public void setSaleId(Long saleId) {
        this.saleId = saleId;
    }

    public Long getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(Long medicineId) {
        this.medicineId = medicineId;
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

    public SaleItem toEntity() {
        return new SaleItem(id, new Sale(saleId), new Medicine(medicineId), batchNo, quantity, unitPrice);
    }
}
