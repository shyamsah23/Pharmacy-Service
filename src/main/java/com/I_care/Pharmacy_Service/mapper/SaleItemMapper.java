package com.I_care.Pharmacy_Service.mapper;

import com.I_care.Pharmacy_Service.entity.SaleItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SaleItemMapper {
    boolean existsBySaleId(Long saleId);

    boolean existsByBatchNo(String batchNo);

    Long addSaleItem(Long saleId, Long medicineId, String batchNo, Integer quantity, Double unitPrice);

    SaleItem findBySaleItemId(Long saleItemId);

    List<SaleItem> findBySaleId(Long saleId);

    List<SaleItem> findByMedicineId(Long medicineId);

    SaleItem findByBatchNo(String batchNo);

    void updateSaleItem(String batchNo, Integer quantity, Double unitPrice);
}
