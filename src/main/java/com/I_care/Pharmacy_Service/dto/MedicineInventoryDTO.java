package com.I_care.Pharmacy_Service.dto;

import com.I_care.Pharmacy_Service.entity.Medicine;
import com.I_care.Pharmacy_Service.entity.MedicineInventory;
import com.I_care.Pharmacy_Service.enums.StockStatus;

import java.time.LocalDate;

public class MedicineInventoryDTO {
    private Long id;
    private Long medicineId;
    private String batchNo;
    private Integer quantity;
    private LocalDate expiryDate;
    private LocalDate addedDate;
    private Integer initialQuantity;
    private StockStatus status;

    public MedicineInventoryDTO() {
    }

    public MedicineInventoryDTO(Long id, Long medicineId, String batchNo, Integer quantity, LocalDate expiryDate, LocalDate addedDate, Integer initialQuantity, StockStatus status) {
        this.id = id;
        this.medicineId = medicineId;
        this.batchNo = batchNo;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
        this.addedDate = addedDate;
        this.initialQuantity = initialQuantity;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public LocalDate getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(LocalDate addedDate) {
        this.addedDate = addedDate;
    }

    public Integer getInitialQuantity() {
        return initialQuantity;
    }

    public void setInitialQuantity(Integer initialQuantity) {
        this.initialQuantity = initialQuantity;
    }

    public StockStatus getStatus() {
        return status;
    }

    public void setStatus(StockStatus status) {
        this.status = status;
    }

    public MedicineInventory toEntity() {
        return new MedicineInventory(id, new Medicine(medicineId), batchNo, quantity, expiryDate, addedDate, initialQuantity, status);
    }
}
