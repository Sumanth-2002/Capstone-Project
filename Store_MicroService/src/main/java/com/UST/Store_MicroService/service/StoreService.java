package com.UST.Store_MicroService.service;

import com.UST.Store_MicroService.dto.*;
import com.UST.Store_MicroService.generator.InventoryIdGenerator;
import com.UST.Store_MicroService.model.Inventory;
import com.UST.Store_MicroService.model.Store;
import com.UST.Store_MicroService.repository.InventoryRepository;
import com.UST.Store_MicroService.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;

@Service
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private InventoryIdGenerator inventoryIdGenerator;
    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private WebClient.Builder webClientBuilder;
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
        Map<String,Object> registerDto = new HashMap<>();
        registerDto.put("userId",store.getStoreId());
        registerDto.put("name",store.getStoreName());
        registerDto.put("password","store@123");
        registerDto.put("role","STORE");
        Optional<Map<String,Object>> response = WebClient.builder()
                .baseUrl("http://localhost:9093")
                .build()
                .post()
                .uri("/api/login/register") // Direct URI without query parameters
                .bodyValue(registerDto) // Attach the DTO as the body
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Optional<Map<String,Object>>>() {})
                .block();
        return "Store details saved successfully ";
    }

    public List<Store> getAllStoreAlone(String companyId) {
        // TODO Auto-generated method stub
        List<Store> stores = storeRepository.findAllStore(companyId);
        return stores;
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
        }
        public Inventory updateStore(UpdateProductDto updateProductDto){
        Inventory inventory = inventoryRepository.getByStoreAndProductId(updateProductDto.getStoreId(),updateProductDto.getProductId());
        inventory.setQuantity(inventory.getQuantity()-updateProductDto.getQuantity());
        return inventoryRepository.save(inventory);
        }
        public Inventory updateQuantity(UpdateProductDto updateProductDto){
        Inventory inventory = inventoryRepository.getByStoreAndProductId(updateProductDto.getStoreId(),updateProductDto.getProductId());
        inventory.setQuantity(inventory.getQuantity()+updateProductDto.getQuantity());
        return inventoryRepository.save(inventory);
        }
}
