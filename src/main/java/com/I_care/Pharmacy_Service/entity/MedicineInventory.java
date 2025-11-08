package com.I_care.Pharmacy_Service.entity;

import com.I_care.Pharmacy_Service.dto.MedicineInventoryDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;

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
    private LocalDate expiryDate;
    private LocalDate addedDate;

    public MedicineInventory() {
    }

    public MedicineInventory(Long id, Medicine medicine, String batchNo, LocalDate expiryDate, LocalDate addedDate) {
        this.id = id;
        this.medicine = medicine;
        this.batchNo = batchNo;
        this.expiryDate = expiryDate;
        this.addedDate = addedDate;
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

    public MedicineInventoryDTO toDTO() {
        return new MedicineInventoryDTO(id, medicine.getId(), batchNo, expiryDate, addedDate);
    }
}
