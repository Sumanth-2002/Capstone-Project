package com.UST.Store_MicroService.service;

import com.UST.Store_MicroService.dto.AddProductDto;
import com.UST.Store_MicroService.dto.InventoryDto;
import com.UST.Store_MicroService.dto.StoreDto;
import com.UST.Store_MicroService.dto.StoreResponseDto;
import com.UST.Store_MicroService.generator.InventoryIdGenerator;
import com.UST.Store_MicroService.model.Inventory;
import com.UST.Store_MicroService.model.Store;
import com.UST.Store_MicroService.repository.InventoryRepository;
import com.UST.Store_MicroService.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private InventoryIdGenerator inventoryIdGenerator;
    @Autowired
    private InventoryRepository inventoryRepository;
    public String  addStoreInventory(StoreDto storeDto) {
        Store store = new Store();
        store.setCompanyId(storeDto.getCompanyId());
        store.setStoreName(storeDto.getStoreName());
        store.setStoreAddress(storeDto.getStoreAddress());
        store.setRegion(storeDto.getRegion());
    if(storeRepository.save(store)==null){
        throw new RuntimeException("Error while saving store");
    };
        Inventory inventory = new Inventory();
        inventory.setInventoryId(inventoryIdGenerator.generateId());
        inventory.setStoreId(store.getStoreId());
        inventory.setProductId(storeDto.getProductId());
        inventory.setQuantity(storeDto.getQuantity());
        inventory.setProductName(storeDto.getProductName());
        if(inventoryRepository.save(inventory)==null){
            throw new RuntimeException("Error while saving store");
        }
        return "Store details saved successfully ";
    }

    public String addNewProduct(AddProductDto addProductDto) {
        Inventory inventory = new Inventory();
        inventory.setInventoryId(inventoryRepository.getInventoryIdByStoreId(addProductDto.getStoreId()));
        inventory.setProductId(addProductDto.getProductId());
        inventory.setQuantity(addProductDto.getQuantity());
        inventory.setProductName(addProductDto.getProductName());
        inventory.setStoreId(addProductDto.getStoreId());
        if(inventoryRepository.save(inventory)==null){
            throw new RuntimeException("Error while saving product");
        }
        return "Product details saved successfully";
    }

    public List<StoreResponseDto> getAllStore(String companyId){
        List<Store> stores = storeRepository.findAllStore(companyId);
        StoreResponseDto storeResponseDto = new StoreResponseDto();
        List<StoreResponseDto> storeResponseDtoList = new ArrayList<>();
        for(Store store : stores){
            storeResponseDto.setStoreId(store.getStoreId());
            storeResponseDto.setCompanyId(store.getCompanyId());
            storeResponseDto.setStoreName(store.getStoreName());
            storeResponseDto.setStoreAddress(store.getStoreAddress());
            storeResponseDto.setRegion(store.getRegion());
            storeResponseDto.setCreatedAt(store.getCreatedAt());
            storeResponseDto.setTotalProducts(inventoryRepository.findByStoreId(store.getStoreId()).stream().mapToLong(InventoryDto::getQuantity).sum());
            storeResponseDto.setInventoryDtos(inventoryRepository.findByStoreId(store.getStoreId()));
            storeResponseDtoList.add(storeResponseDto);
        }
        return storeResponseDtoList;
    }
    public StoreResponseDto getStore(String storeId) {
        StoreResponseDto storeResponseDto = new StoreResponseDto();
       Optional<Store> se =  storeRepository.findById(storeId);
       if(!se.isPresent()) throw new RuntimeException("Store not found");
           Store store = se.get();
           storeResponseDto.setStoreId(store.getStoreId());
           storeResponseDto.setCompanyId(store.getCompanyId());
           storeResponseDto.setStoreName(store.getStoreName());
           storeResponseDto.setStoreAddress(store.getStoreAddress());
           storeResponseDto.setRegion(store.getRegion());
           storeResponseDto.setCreatedAt(store.getCreatedAt());
           storeResponseDto.setTotalProducts(inventoryRepository.findByStoreId(store.getStoreId()).stream().mapToLong(InventoryDto::getQuantity).sum());
           storeResponseDto.setInventoryDtos(inventoryRepository.findByStoreId(store.getStoreId()));
        return storeResponseDto;
        }}
