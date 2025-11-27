package com.I_care.Pharmacy_Service.service;

import com.I_care.Pharmacy_Service.dto.MedicineInventoryDTO;
import com.I_care.Pharmacy_Service.entity.MedicineInventory;
import com.I_care.Pharmacy_Service.enums.StockStatus;
import com.I_care.Pharmacy_Service.exception.PharmacyException;
import com.I_care.Pharmacy_Service.repository.MedicineInventoryRepository;
import com.I_care.Pharmacy_Service.utility.PharmacyConstant;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class MedicineInventoryServiceImpl implements MedicineInventoryService {

    @Autowired
    private MedicineInventoryRepository medicineInventoryRepository;

    @Autowired
    private MedicineService medicineService;

    Logger logger = LoggerFactory.getLogger(MedicineInventory.class);

    @Override
    public MedicineInventoryDTO addMedicine(MedicineInventoryDTO medicineInventoryDTO) throws PharmacyException {
        logger.info("New stock being added for medicine with id={} & quantity={}", medicineInventoryDTO.getMedicineId(), medicineInventoryDTO.getQuantity());
        medicineInventoryDTO.setAddedDate(LocalDate.now());
        medicineService.addStockById(medicineInventoryDTO.getMedicineId(), medicineInventoryDTO.getQuantity());
        medicineInventoryDTO.setInitialQuantity(medicineInventoryDTO.getQuantity());
        medicineInventoryDTO.setStatus(StockStatus.NOT_EXPIRED);
        logger.info("Medicine batch has been added successfully to inventory");
        return medicineInventoryRepository.save(medicineInventoryDTO.toEntity()).toDTO();
    }

    @Override
    public MedicineInventoryDTO updateMedicine(MedicineInventoryDTO medicineInventoryDTO) throws PharmacyException {
        MedicineInventoryDTO existingInventory = medicineInventoryRepository.findById(medicineInventoryDTO.getId()).orElseThrow(() -> new PharmacyException(PharmacyConstant.MEDICINE_NOT_FOUND)).toDTO();
        if (existingInventory == null) {
            throw new PharmacyException(PharmacyConstant.MEDICINE_NOT_FOUND);
        }
        if (existingInventory.getQuantity() <= medicineInventoryDTO.getQuantity()) {
            medicineService.addStockById(medicineInventoryDTO.getId(), medicineInventoryDTO.getQuantity() - existingInventory.getQuantity());
        } else {
            medicineService.removeStockById(medicineInventoryDTO.getId(), existingInventory.getQuantity() - medicineInventoryDTO.getQuantity());
        }
        existingInventory.setQuantity(medicineInventoryDTO.getQuantity());
        existingInventory.setInitialQuantity(medicineInventoryDTO.getQuantity());
        existingInventory.setMedicineId(medicineInventoryDTO.getMedicineId());
        existingInventory.setBatchNo(medicineInventoryDTO.getBatchNo());
        existingInventory.setExpiryDate(medicineInventoryDTO.getExpiryDate());
        return existingInventory;
    }

    @Override
    public MedicineInventoryDTO getMedicineById(Long id) throws PharmacyException {
        return medicineInventoryRepository.findById(id).orElseThrow(() -> new PharmacyException(PharmacyConstant.MEDICINE_NOT_FOUND)).toDTO();
    }

    @Override
    public List<MedicineInventoryDTO> getAllMedicines() throws PharmacyException {
        return ((List<MedicineInventory>) medicineInventoryRepository.findAll()).stream().map(MedicineInventory::toDTO).toList();
    }

    @Override
    public void deleteMedicineById(Long id) throws PharmacyException {
        medicineInventoryRepository.deleteById(id);
    }

    private void markExpired(List<MedicineInventory> inventories) throws PharmacyException {
        for (MedicineInventory inventory : inventories) {
            inventory.setStatus(StockStatus.EXPIRED);
        }
        medicineInventoryRepository.saveAll(inventories);
    }

    @Override
    @Scheduled(cron = PharmacyConstant.CRON_JOB)
    public void deleteAllExpiredMedicines() throws PharmacyException {
        logger.info("Expired Medicines being deleted now");
        List<MedicineInventory> expiredMedicines = medicineInventoryRepository.findByExpiryDateBefore(LocalDate.now());
        for (MedicineInventory medicine : expiredMedicines) {
            medicineService.removeStockById(medicine.getMedicine().getId(), medicine.getQuantity());
        }
        logger.info("Expired Medicines deleted");
        this.markExpired(expiredMedicines);
    }

}
