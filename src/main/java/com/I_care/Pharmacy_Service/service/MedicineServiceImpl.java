package com.I_care.Pharmacy_Service.service;

import com.I_care.Pharmacy_Service.dto.MedicineDTO;
import com.I_care.Pharmacy_Service.entity.Medicine;
import com.I_care.Pharmacy_Service.exception.PharmacyException;
import com.I_care.Pharmacy_Service.repository.MedicineRepository;
import com.I_care.Pharmacy_Service.utility.PharmacyConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
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
}
