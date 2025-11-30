package com.I_care.Pharmacy_Service.service;

import com.I_care.Pharmacy_Service.dto.MedicineDTO;
import com.I_care.Pharmacy_Service.entity.Medicine;
import com.I_care.Pharmacy_Service.exception.PharmacyException;
import com.I_care.Pharmacy_Service.repository.MedicineRepository;
import com.I_care.Pharmacy_Service.utility.PharmacyConstant;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MedicineServiceImpl implements MedicineService {

    Logger logger = LoggerFactory.getLogger(MedicineServiceImpl.class);

    @Autowired
    private MedicineRepository medicineRepository;

    @Override
    public Long addMedicine(MedicineDTO medicineDTO) throws PharmacyException {
        logger.info("Medicine with id ={},getting added to db", medicineDTO.getId());
        Optional<Medicine> optional = medicineRepository.findByNameIgnoreCaseAndDosageIgnoreCase(medicineDTO.getName(), medicineDTO.getDosage());
        if (optional.isPresent()) {
            logger.info("Medicine with id ={},already exists in db", medicineDTO.getId());
            throw new PharmacyException(PharmacyConstant.MEDICINE_ALREADY_EXISTS);
        }
        medicineDTO.setCreatedAt(LocalDateTime.now());
        medicineDTO.setStock(0);
        logger.info("Medicine with id ={},added to db", medicineDTO.getId());
        return medicineRepository.save(medicineDTO.toEntity()).getId();
    }

    @Override
    public MedicineDTO getMedicineById(Long id) throws PharmacyException {
        return medicineRepository.findById(id).orElseThrow(() -> new PharmacyException(PharmacyConstant.MEDICINE_NOT_FOUND)).toDTO();
    }

    @Override
    public void updateMedicine(MedicineDTO medicineDTO) throws PharmacyException {
        logger.info("Updating Medicine details with id ={} in db", medicineDTO.getId());
        MedicineDTO existingMedicine = medicineRepository.findById(medicineDTO.getId()).orElseThrow(() -> new PharmacyException(PharmacyConstant.MEDICINE_NOT_FOUND)).toDTO();
        if (!medicineDTO.getName().equalsIgnoreCase(existingMedicine.getName()) && !medicineDTO.getDosage().equalsIgnoreCase(existingMedicine.getDosage())) {
            Optional<Medicine> optional = medicineRepository.findByNameIgnoreCaseAndDosageIgnoreCase(medicineDTO.getName(), medicineDTO.getDosage());
            if (optional.isPresent()) {
                logger.info("Medicine with id ={},already exists in db", medicineDTO.getId());
                throw new PharmacyException(PharmacyConstant.MEDICINE_ALREADY_EXISTS);
            }
        }
        existingMedicine.setName(medicineDTO.getName());
        existingMedicine.setDosage(medicineDTO.getDosage());
        existingMedicine.setManufacturer(medicineDTO.getManufacturer());
        existingMedicine.setMedicineType(medicineDTO.getMedicineType());
        existingMedicine.setMedicineCategory(medicineDTO.getMedicineCategory());
        existingMedicine.setUnitPrice(medicineDTO.getUnitPrice());
        logger.info("Medicine details updated");
    }

    @Override
    public List<MedicineDTO> getAllMedicines() throws PharmacyException {
        return ((List<Medicine>) medicineRepository.findAll()).stream().map(Medicine::toDTO).toList();
    }

    @Override
    public Integer getStockById(Long id) throws PharmacyException {
        return medicineRepository.findStockById(id).orElseThrow(() -> new PharmacyException(PharmacyConstant.ZERO_MEDICINE_QUANTITY));
    }

    @Override
    public Integer addStockById(Long id, Integer qty) throws PharmacyException {
        logger.info("Medicine with id={} having quantity={} being added", id, qty);
        Medicine meds = medicineRepository.findById(id).orElseThrow(() -> new PharmacyException(PharmacyConstant.MEDICINE_NOT_FOUND));
        meds.setStock(meds.getStock() + qty);
        logger.info("New stock for given medicine is ={}", meds.getStock());
        medicineRepository.save(meds);
        logger.info("Medicines added to inventory");
        return meds.getStock();
    }

    @Override
    public Integer removeStockById(Long id, Integer qty) throws PharmacyException {
        logger.info("Medicine with id={} having quantity={} being removed from inventory", id, qty);
        Medicine meds = medicineRepository.findById(id).orElseThrow(() -> new PharmacyException(PharmacyConstant.MEDICINE_NOT_FOUND));
        if (meds.getStock() < qty) meds.setStock(0);
        else meds.setStock(meds.getStock() - qty);
        medicineRepository.save(meds);
        logger.info("Medicines removed from inventory");
        return meds.getStock();
    }
}
