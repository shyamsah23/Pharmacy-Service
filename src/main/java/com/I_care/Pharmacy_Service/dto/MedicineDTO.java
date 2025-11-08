package com.I_care.Pharmacy_Service.dto;

import com.I_care.Pharmacy_Service.entity.Medicine;
import com.I_care.Pharmacy_Service.enums.MedicineCategory;
import com.I_care.Pharmacy_Service.enums.MedicineType;

import java.time.LocalDateTime;

public class MedicineDTO {
    private Long id;
    private String name;
    private String dosage;
    private MedicineCategory medicineCategory;
    private MedicineType medicineType;
    private String manufacturer;
    private Double unitPrice;
    private LocalDateTime createdAt;

    public MedicineDTO() {
    }

    public MedicineDTO(Long id, String name, String dosage, MedicineCategory medicineCategory, MedicineType medicineType, String manufacturer, Double unitPrice, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.dosage = dosage;
        this.medicineCategory = medicineCategory;
        this.medicineType = medicineType;
        this.manufacturer = manufacturer;
        this.unitPrice = unitPrice;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public MedicineCategory getMedicineCategory() {
        return medicineCategory;
    }

    public void setMedicineCategory(MedicineCategory medicineCategory) {
        this.medicineCategory = medicineCategory;
    }

    public MedicineType getMedicineType() {
        return medicineType;
    }

    public void setMedicineType(MedicineType medicineType) {
        this.medicineType = medicineType;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Medicine toEntity() {
        return new Medicine(id, name, dosage, medicineCategory, medicineType, manufacturer, unitPrice, createdAt);
    }
}
