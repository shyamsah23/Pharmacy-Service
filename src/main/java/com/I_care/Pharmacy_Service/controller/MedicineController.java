package com.I_care.Pharmacy_Service.controller;

import com.I_care.Pharmacy_Service.dto.MedicineDTO;
import com.I_care.Pharmacy_Service.dto.ResponseDTO;
import com.I_care.Pharmacy_Service.exception.PharmacyException;
import com.I_care.Pharmacy_Service.service.MedicineService;
import com.I_care.Pharmacy_Service.utility.PharmacyConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.List;

@RestController
@RequestMapping("/pharmacy/medicines")
public class MedicineController {

    private final MedicineService medicineService;

    public MedicineController(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    @PostMapping("/add")
    public ResponseEntity<Long> addMedicine(@RequestBody MedicineDTO medicineDTO) throws PharmacyException {
        return new ResponseEntity<>(medicineService.addMedicine(medicineDTO), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<MedicineDTO> getMedicineById(@PathVariable Long id) throws PharmacyException {
        return new ResponseEntity<>(medicineService.getMedicineById(id), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> updateMedicine(@RequestBody MedicineDTO medicineDTO) throws PharmacyException {
        medicineService.updateMedicine(medicineDTO);
        return new ResponseEntity<>(new ResponseDTO(PharmacyConstant.MEDICINE_UPDATED), HttpStatus.OK);
    }

    @Scheduled()
    @GetMapping("/getAll")
    public ResponseEntity<List<MedicineDTO>> getAllMedicines() throws PharmacyException {
        return new ResponseEntity<>(medicineService.getAllMedicines(), HttpStatus.OK);
    }
}
