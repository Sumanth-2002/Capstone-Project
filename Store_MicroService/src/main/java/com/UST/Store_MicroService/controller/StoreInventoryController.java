package com.UST.Store_MicroService.controller;

import com.UST.Store_MicroService.model.Store;
import com.UST.Store_MicroService.model.Inventory;
import com.UST.Store_MicroService.service.StoreService;
import com.UST.Store_MicroService.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/stores")
public class StoreInventoryController {

    @Autowired
    private StoreService storeService;

    @Autowired
    private InventoryService inventoryService;

    // Store Endpoints
    @PostMapping
    public Store createStore(@RequestBody Store store) {
        return storeService.createStore(store);
    }

    @GetMapping
    public List<Store> getAllStores() {
        return storeService.getAllStores();
    }

    @GetMapping("/{id}")
    public Optional<Store> getStoreById(@PathVariable("id") String id) {
        return storeService.getStoreById(id);
    }

    @PutMapping("/{id}")
    public Store updateStore(@PathVariable("id") String id, @RequestBody Store store) {
        return storeService.updateStore(id, store);
    }

    @DeleteMapping("/{id}")
    public void deleteStore(@PathVariable("id") String id) {
        storeService.deleteStore(id);
    }

    // Inventory Endpoints for a specific Store
    @PostMapping("/{storeId}/inventory")
    public Inventory createInventory(@PathVariable("storeId") String storeId, @RequestBody Inventory inventory) {
        Optional<Store> storeOpt = storeService.getStoreById(storeId);
        if (storeOpt.isPresent()) {
            Store store = storeOpt.get();
            inventory.setStore(store); // Link the inventory to the store
            return inventoryService.createInventory(inventory);
        }
        return null; // Or throw an exception indicating store not found
    }

    @GetMapping("/{storeId}/inventory")
    public Optional<Inventory> getInventoryByStore(@PathVariable("storeId") String storeId) {
        Optional<Store> storeOpt = storeService.getStoreById(storeId);
        if (storeOpt.isPresent()) {
            Store store = storeOpt.get();
            return Optional.ofNullable(store.getInventory());
        }
        return Optional.empty(); // Return empty if no inventory found
    }

    @PutMapping("/{storeId}/inventory")
    public Inventory updateInventory(@PathVariable("storeId") String storeId, @RequestBody Inventory inventory) {
        Optional<Store> storeOpt = storeService.getStoreById(storeId);
        if (storeOpt.isPresent()) {
            Store store = storeOpt.get();
            inventory.setStore(store); // Ensure the inventory belongs to the correct store
            return inventoryService.updateInventory(inventory.getId(), inventory);
        }
        return null; // Or throw an exception indicating store not found
    }

    @DeleteMapping("/{storeId}/inventory")
    public void deleteInventory(@PathVariable("storeId") String storeId) {
        Optional<Store> storeOpt = storeService.getStoreById(storeId);
        if (storeOpt.isPresent()) {
            Store store = storeOpt.get();
            Inventory inventory = store.getInventory();
            if (inventory != null) {
                inventoryService.deleteInventory(inventory.getId());
            }
        }
    }
}
