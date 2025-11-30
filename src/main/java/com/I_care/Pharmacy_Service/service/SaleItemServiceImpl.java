package com.I_care.Pharmacy_Service.service;

import com.I_care.Pharmacy_Service.dto.SaleItemDTO;
import com.I_care.Pharmacy_Service.entity.SaleItem;
import com.I_care.Pharmacy_Service.exception.PharmacyException;
import com.I_care.Pharmacy_Service.mapper.SaleItemMapper;
import com.I_care.Pharmacy_Service.utility.PharmacyConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleItemServiceImpl implements SaleItemService {

    @Autowired
    private SaleItemMapper saleItemMapper;

    Logger logger = LoggerFactory.getLogger(SaleItemServiceImpl.class);

    @Override
    public Long createSaleItem(SaleItemDTO saleItemDTO) throws PharmacyException {
        logger.info("Sales item being added with details={}", saleItemDTO);
        if (saleItemMapper.existsByBatchNo(saleItemDTO.getBatchNo()))
            throw new PharmacyException(PharmacyConstant.SALES_ITEM_HAVING_GIVEN_BATCH_NUMBER_ALREADY_EXISTS);
        SaleItem saleItemEntity = saleItemDTO.toEntity();
        saleItemMapper.addSaleItem(saleItemEntity.getSale().getId(), saleItemEntity.getMedicine().getId(), saleItemEntity.getBatchNo(), saleItemEntity.getQuantity(), saleItemEntity.getUnitPrice());
        return saleItemMapper.findByBatchNo(saleItemDTO.getBatchNo()).getId();
    }

    @Override
    public void createMultipleSaleItem(List<SaleItemDTO> saleItemDTOs) throws PharmacyException {
        logger.info("Multiple sales item being created");
        saleItemDTOs.stream().map(SaleItemDTO::toEntity).forEach(x -> saleItemMapper.addSaleItem(x.getSale().getId(), x.getMedicine().getId(), x.getBatchNo(), x.getQuantity(), x.getUnitPrice()));
        logger.info("Sales items have been added");
    }

    @Override
    public void updateSaleItem(SaleItemDTO saleItemDTO) throws PharmacyException {
        logger.info("Sales item details being updated for id={}", saleItemDTO.getSaleId());
        if (saleItemMapper.existsBySaleId(saleItemDTO.getSaleId())) {
            logger.info("Sales item does not exist");
            throw new PharmacyException(PharmacyConstant.SALES_ITEM_DOES_NOT_EXISTS);
        }
        SaleItem exisitingSaleItem = saleItemMapper.findBySaleItemId(saleItemDTO.getId());
        exisitingSaleItem.setQuantity(saleItemDTO.getQuantity());
        exisitingSaleItem.setUnitPrice(saleItemDTO.getUnitPrice());
        saleItemMapper.updateSaleItem(exisitingSaleItem.getBatchNo(), exisitingSaleItem.getQuantity(), exisitingSaleItem.getUnitPrice());
        logger.info("Sale item updated successfully");
    }

    @Override
    public List<SaleItemDTO> getSaleItemsBySaleId(Long saleId) throws PharmacyException {
        logger.info("Sales item being fetched with saleId={}", saleId);
        return saleItemMapper.findBySaleId(saleId).stream()
                .map(SaleItem::toDTO).toList();
    }

    @Override
    public SaleItemDTO getSaleItem(Long id) throws PharmacyException {
        logger.info("Sales item being fetched with id={}", id);
        SaleItem exisitingSaleItem = saleItemMapper.findBySaleItemId(id);
        if (exisitingSaleItem == null) {
            logger.info("Sales item does not exist");
            throw new PharmacyException(PharmacyConstant.SALES_ITEM_DOES_NOT_EXISTS);
        }
        return exisitingSaleItem.toDTO();
    }
}
