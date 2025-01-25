package com.UST.Store_MicroService.service;

import com.UST.Store_MicroService.model.Store;
import com.UST.Store_MicroService.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;

    public Store createStore(Store store) {
        return storeRepository.save(store);
    }

    public Store updateStore(String storeId, Store storeDetails) {
        Optional<Store> storeOpt = storeRepository.findById(storeId);
        if (storeOpt.isPresent()) {
            Store store = storeOpt.get();
            store.setName(storeDetails.getName());
            store.setAddress(storeDetails.getAddress());
            store.setRegion(storeDetails.getRegion());
            store.setCompanyId(storeDetails.getCompanyId());
            store.setLastUpdated(storeDetails.getLastUpdated());

            // Handle updating the inventory associated with the store
            if (storeDetails.getInventory() != null) {
                store.getInventory().setQuantity(storeDetails.getInventory().getQuantity());
                store.getInventory().setLastUpdated(storeDetails.getInventory().getLastUpdated());
            }
            return storeRepository.save(store);
        }
        return null;
    }

    public void deleteStore(String storeId) {
        storeRepository.deleteById(storeId);
    }

    public List<Store> getAllStores() {
        return storeRepository.findAll();
    }

    public Optional<Store> getStoreById(String storeId) {
        return storeRepository.findById(storeId);
    }
}
