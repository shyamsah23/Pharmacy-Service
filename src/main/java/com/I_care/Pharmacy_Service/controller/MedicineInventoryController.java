package com.I_care.Pharmacy_Service.controller;

import com.I_care.Pharmacy_Service.dto.MedicineInventoryDTO;
import com.I_care.Pharmacy_Service.dto.ResponseDTO;
import com.I_care.Pharmacy_Service.exception.PharmacyException;
import com.I_care.Pharmacy_Service.service.MedicineInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory/medicines")
public class MedicineInventoryController {

    @Autowired
    private MedicineInventoryService medicineInventoryService;

    @PostMapping("/add")
    public ResponseEntity<MedicineInventoryDTO>addMedicineToInventory(@RequestBody MedicineInventoryDTO medicineInventoryDTO) throws PharmacyException {
        return new ResponseEntity<>(medicineInventoryService.addMedicine(medicineInventoryDTO), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<MedicineInventoryDTO>updateMedicineInInventory(@RequestBody MedicineInventoryDTO medicineInventoryDTO) throws PharmacyException{
        return new ResponseEntity<>(medicineInventoryService.updateMedicine(medicineInventoryDTO), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<MedicineInventoryDTO>getMedicineById(@PathVariable Long id) throws PharmacyException{
        return new ResponseEntity<>(medicineInventoryService.getMedicineById(id),HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<MedicineInventoryDTO>>getAllMedicines(@PathVariable Long id) throws PharmacyException{
        return new ResponseEntity<>(medicineInventoryService.getAllMedicines(),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO>deleteMedicineById(@PathVariable Long id) throws PharmacyException{
        medicineInventoryService.deleteMedicineById(id);
        return new ResponseEntity<>(new ResponseDTO("Medicine Deleted successfully"),HttpStatus.OK);
    }

//    @Scheduled(cron="0 0 0 * * *")
//    @DeleteMapping("/delete/expiredMedicines")
//    public void deleteExpiredMedicines() throws PharmacyException{
//        medicineInventoryService.deleteAllExpiredMedicines();
//    }
}
