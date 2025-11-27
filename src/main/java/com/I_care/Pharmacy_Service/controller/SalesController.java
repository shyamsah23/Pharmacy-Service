package com.I_care.Pharmacy_Service.controller;

import com.I_care.Pharmacy_Service.dto.ResponseDTO;
import com.I_care.Pharmacy_Service.dto.SaleDTO;
import com.I_care.Pharmacy_Service.dto.SaleItemDTO;
import com.I_care.Pharmacy_Service.exception.PharmacyException;
import com.I_care.Pharmacy_Service.service.SaleItemService;
import com.I_care.Pharmacy_Service.service.SaleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pharmacy/sales")
public class SalesController {
    @Autowired
    private SaleService saleService;

    @Autowired
    private SaleItemService saleItemService;

    Logger logger = LoggerFactory.getLogger(SalesController.class);

    @PostMapping("/create")
    public ResponseEntity<Long> createSale(@RequestBody SaleDTO saleDTO) throws PharmacyException {
        logger.info("Trying to add sales order with prescription id={}", saleDTO.getPrescriptionId());
        return new ResponseEntity<>(saleService.createSale(saleDTO), HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<ResponseDTO> updateSale(@RequestBody SaleDTO saleDTO) throws PharmacyException {
        logger.info("Trying to update sales order with prescription id={}", saleDTO.getPrescriptionId());
        saleService.updateSale(saleDTO);
        return new ResponseEntity<>(new ResponseDTO("Sale data updated successfully"), HttpStatus.OK);
    }

    @PostMapping("/saleItem/create")
    public ResponseEntity<Long> createSaleItem(@RequestBody SaleItemDTO saleItemDTO) throws PharmacyException {
        logger.info("Trying to add sales order with batch no={}", saleItemDTO.getBatchNo());
        return new ResponseEntity<>(saleItemService.createSaleItem(saleItemDTO), HttpStatus.OK);
    }

    @PostMapping("/saleItem/update")
    public ResponseEntity<ResponseDTO> updateSaleItem(@RequestBody SaleItemDTO saleItemDTO) throws PharmacyException {
        logger.info("Trying to update sales order with batch no={}", saleItemDTO.getBatchNo());
        saleItemService.updateSaleItem(saleItemDTO);
        return new ResponseEntity<>(new ResponseDTO("Sale item data updated successfully"), HttpStatus.OK);
    }

    @PostMapping("/saleItem/createMultiple")
    public ResponseEntity<ResponseDTO> createMultipleSaleItem(@RequestBody List<SaleItemDTO> saleItemsDTO) throws PharmacyException {
        logger.info("Trying to add multiple sales item");
        saleItemService.createMultipleSaleItem(saleItemsDTO);
        return new ResponseEntity<>(new ResponseDTO("Sales items added"), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<SaleDTO> getSale(@PathVariable Long id) throws PharmacyException {
        logger.info("Fetching sales order with id={}", id);
        return new ResponseEntity<>(saleService.getSale(id), HttpStatus.OK);
    }

    @GetMapping("/saleItem/get/{saleId}")
    public ResponseEntity<List<SaleItemDTO>> getSaleItems(@PathVariable Long saleId) throws PharmacyException {
        logger.info("Trying to fetch sale items with sale id={}", saleId);
        List<SaleItemDTO> saleItems = saleItemService.getSaleItemsBySaleId(saleId);
        return new ResponseEntity<>(saleItems, HttpStatus.OK);
    }

    @GetMapping("/saleItem/get/{id}")
    public ResponseEntity<SaleItemDTO> getSaleItemById(@PathVariable Long id) throws PharmacyException {
        logger.info("Fetching sale item with id ={}", id);
        return new ResponseEntity<>(saleItemService.getSaleItem(id), HttpStatus.OK);
    }
}
