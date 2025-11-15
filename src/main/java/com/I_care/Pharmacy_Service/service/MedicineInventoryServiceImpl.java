package com.I_care.Pharmacy_Service.service;

import com.I_care.Pharmacy_Service.dto.MedicineInventoryDTO;
import com.I_care.Pharmacy_Service.entity.MedicineInventory;
import com.I_care.Pharmacy_Service.exception.PharmacyException;
import com.I_care.Pharmacy_Service.repository.MedicineInventoryRepository;
import com.I_care.Pharmacy_Service.repository.MedicineRepository;
import com.I_care.Pharmacy_Service.utility.PharmacyConstant;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class MedicineInventoryServiceImpl implements MedicineInventoryService{

    @Autowired
    private MedicineInventoryRepository medicineInventoryRepository;

    @Autowired
    private MedicineService medicineService;

    @Autowired
    private MedicineRepository medicineRepository;

    @Override
    public MedicineInventoryDTO addMedicine(MedicineInventoryDTO medicineInventoryDTO) throws PharmacyException {
        medicineInventoryDTO.setAddedDate(LocalDate.now());
        medicineService.addStockById(medicineInventoryDTO.getId(),medicineInventoryDTO.getQuantity());
        return medicineInventoryRepository.save(medicineInventoryDTO.toEntity()).toDTO();
    }

    @Override
    public MedicineInventoryDTO updateMedicine(MedicineInventoryDTO medicineInventoryDTO) throws PharmacyException {
        MedicineInventoryDTO existingInventory = medicineInventoryRepository.findById(medicineInventoryDTO.getId()).orElseThrow(()->new PharmacyException(PharmacyConstant.MEDICINE_NOT_FOUND)).toDTO();
        if (existingInventory==null) {
            throw new PharmacyException(PharmacyConstant.MEDICINE_NOT_FOUND);
        }
        if(existingInventory.getQuantity()<= medicineInventoryDTO.getQuantity()){
            medicineService.addStockById(medicineInventoryDTO.getId(), medicineInventoryDTO.getQuantity()-existingInventory.getQuantity());
        }
        else {
            medicineService.removeStockById(medicineInventoryDTO.getId(), existingInventory.getQuantity()-medicineInventoryDTO.getQuantity());
        }
        existingInventory.setQuantity(medicineInventoryDTO.getQuantity());
        existingInventory.setMedicineId(medicineInventoryDTO.getMedicineId());
        existingInventory.setBatchNo(medicineInventoryDTO.getBatchNo());
        existingInventory.setExpiryDate(medicineInventoryDTO.getExpiryDate());
        return existingInventory;
    }

    @Override
    public MedicineInventoryDTO getMedicineById(Long id) throws PharmacyException {
        return medicineInventoryRepository.findById(id).orElseThrow(()->new PharmacyException(PharmacyConstant.MEDICINE_NOT_FOUND)).toDTO();
    }

    @Override
    public List<MedicineInventoryDTO> getAllMedicines() throws PharmacyException {
        return ((List<MedicineInventory>)medicineInventoryRepository.findAll()).stream().map(MedicineInventory::toDTO).toList();
    }

    @Override
    public void deleteMedicineById(Long id) throws PharmacyException {
        medicineInventoryRepository.deleteById(id);
    }

//    @Override
//    public void deleteAllExpiredMedicines() throws PharmacyException{
//      medicineInventoryRepository.findAll()
//                        .stream()
//                        .map(MedicineInventory::toDTO)
//                        .filter(dto -> dto.getExpiryDate().isBefore(LocalDate.now()))
//                        .forEach(dto->{
//                                medicineInventoryRepository.deleteById(dto.getId());
//                                medicineRepository.findById(dto.getId()).);
//                        });
//    }
}
