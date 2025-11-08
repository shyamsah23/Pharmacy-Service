package com.I_care.Pharmacy_Service.repository;

import com.I_care.Pharmacy_Service.entity.MedicineInventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineInventoryRepository extends JpaRepository<MedicineInventory, Long> {
}
