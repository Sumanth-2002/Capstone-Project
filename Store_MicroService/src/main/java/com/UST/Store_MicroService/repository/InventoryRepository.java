package com.UST.Store_MicroService.repository;

import com.UST.Store_MicroService.dto.InventoryDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.UST.Store_MicroService.model.Inventory;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory,Long>{

    @Query("SELECT COUNT(DISTINCT i.inventoryId) FROM Inventory i")
    Long getNumberOfUniqueInventory();

    @Query("SELECT i.inventoryId FROM Inventory i where i.storeId = :storeId")
    String getInventoryIdByStoreId(@Param("storeId") String storeId);

    @Query("SELECT i.productId,i.productName,i.quantity FROM Inventory i where i.storeId = :storeId")
    List<InventoryDto> findByStoreId(@Param("storeId") String storeId);
}
