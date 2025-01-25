package com.UST.Store_MicroService.service;

import com.UST.Store_MicroService.model.Inventory;
import com.UST.Store_MicroService.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    public Inventory createInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    public Inventory updateInventory(Long inventoryId, Inventory inventoryDetails) {
        Optional<Inventory> inventoryOpt = inventoryRepository.findById(inventoryId);
        if (inventoryOpt.isPresent()) {
            Inventory inventory = inventoryOpt.get();
            inventory.setQuantity(inventoryDetails.getQuantity());
            inventory.setLastUpdated(inventoryDetails.getLastUpdated());
            return inventoryRepository.save(inventory);
        }
        return null;
    }

    public void deleteInventory(Long inventoryId) {
        inventoryRepository.deleteById(inventoryId);
    }

    public List<Inventory> getAllInventories() {
        return inventoryRepository.findAll();
    }

    public Optional<Inventory> getInventoryById(Long inventoryId) {
        return inventoryRepository.findById(inventoryId);
    }
}
