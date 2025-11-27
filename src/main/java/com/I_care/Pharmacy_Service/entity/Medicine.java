package com.I_care.Pharmacy_Service.entity;

import com.I_care.Pharmacy_Service.dto.MedicineDTO;
import com.I_care.Pharmacy_Service.enums.MedicineCategory;
import com.I_care.Pharmacy_Service.enums.MedicineType;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "pharmacy",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"name", "dosage"})
        }
)
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String dosage;
    private MedicineCategory medicineCategory;
    private MedicineType medicineType;
    private String manufacturer;
    private Double unitPrice;
    private Integer stock;
    private LocalDateTime createdAt;

    public Medicine() {
    }

    public Medicine(Long id) {
        this.id = id;
    }

    public Medicine(Long id, String name, String dosage, MedicineCategory medicineCategory, MedicineType medicineType, String manufacturer, Double unitPrice, Integer stock, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.dosage = dosage;
        this.medicineCategory = medicineCategory;
        this.medicineType = medicineType;
        this.manufacturer = manufacturer;
        this.unitPrice = unitPrice;
        this.stock = stock;
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

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public MedicineDTO toDTO() {
        return new MedicineDTO(id, name, dosage, medicineCategory, medicineType, manufacturer, unitPrice, stock, createdAt);
    }

}
