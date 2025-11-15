package com.I_care.Pharmacy_Service.service;

import com.I_care.Pharmacy_Service.dto.MedicineInventoryDTO;
import com.I_care.Pharmacy_Service.exception.PharmacyException;

import java.util.List;

public interface MedicineInventoryService {
    public MedicineInventoryDTO addMedicine(MedicineInventoryDTO medicineInventoryDTO) throws PharmacyException;
    public MedicineInventoryDTO updateMedicine(MedicineInventoryDTO medicineInventoryDTO) throws PharmacyException;
    public MedicineInventoryDTO getMedicineById(Long id) throws PharmacyException;
    public List<MedicineInventoryDTO>getAllMedicines() throws PharmacyException;
    public void deleteMedicineById(Long id) throws PharmacyException;
    public void deleteAllExpiredMedicines() throws PharmacyException;
}
