package com.I_care.Pharmacy_Service.service;

import com.I_care.Pharmacy_Service.dto.MedicineDTO;
import com.I_care.Pharmacy_Service.entity.Medicine;
import com.I_care.Pharmacy_Service.exception.PharmacyException;

import java.util.List;

public interface MedicineService {

    public Long addMedicine(MedicineDTO medicineDTO) throws PharmacyException;

    public MedicineDTO getMedicineById(Long id) throws PharmacyException;

    public void updateMedicine(MedicineDTO medicineDTO) throws PharmacyException;

    public List<MedicineDTO> getAllMedicines() throws PharmacyException;

    public Integer getStockById(Long id) throws PharmacyException;

    public Integer addStockById(Long id, Integer qty) throws PharmacyException;

    public Integer removeStockById(Long id, Integer qty) throws PharmacyException;
}
