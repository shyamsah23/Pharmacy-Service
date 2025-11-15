package com.I_care.Pharmacy_Service.repository;

import com.I_care.Pharmacy_Service.entity.MedicineInventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface MedicineInventoryRepository extends JpaRepository<MedicineInventory, Long> {
    List<MedicineInventory>findByExpiryDataBefore(LocalDate date);
}
