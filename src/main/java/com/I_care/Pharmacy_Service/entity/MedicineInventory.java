package com.I_care.Pharmacy_Service.entity;

import com.I_care.Pharmacy_Service.dto.MedicineInventoryDTO;
import com.I_care.Pharmacy_Service.enums.StockStatus;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class MedicineInventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medicine_id", nullable = false)
    private Medicine medicine;
    private String batchNo;
    private Integer quantity;
    private LocalDate expiryDate;
    private LocalDate addedDate;
    private Integer initialQuantity;
    @Enumerated(EnumType.STRING)
    private StockStatus status;

    public MedicineInventory() {
    }

    public MedicineInventory(Long id, Medicine medicine, String batchNo, Integer quantity, LocalDate expiryDate, LocalDate addedDate, Integer initialQuantity, StockStatus status) {
        this.id = id;
        this.medicine = medicine;
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

    public MedicineInventoryDTO toDTO() {
        return new MedicineInventoryDTO(id, medicine.getId(), batchNo, quantity, expiryDate, addedDate, initialQuantity, status);
    }
}
