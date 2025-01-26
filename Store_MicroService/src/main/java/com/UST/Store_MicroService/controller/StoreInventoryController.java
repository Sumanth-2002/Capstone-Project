package com.UST.Store_MicroService.controller;

import com.UST.Store_MicroService.dto.AddProductDto;
import com.UST.Store_MicroService.dto.StoreDto;
import com.UST.Store_MicroService.dto.StoreResponseDto;
import com.UST.Store_MicroService.model.Request;
import com.UST.Store_MicroService.service.RequestService;
import com.UST.Store_MicroService.service.StoreService;
//import com.UST.Store_MicroService.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stores")
public class StoreInventoryController {

    @Autowired
    private StoreService storeService;
    @Autowired
    private RequestService requestService;

    @PostMapping("/addStore")
    public ResponseEntity<String> addStore(@RequestBody StoreDto storeDto) {
        return ResponseEntity.ok(storeService.addStoreInventory(storeDto));
    }
    @PostMapping("/addNewProduct")
    public ResponseEntity<String> addNewProduct(@RequestBody AddProductDto addProductDto) {
        return ResponseEntity.ok(storeService.addNewProduct(addProductDto));
    }

    @GetMapping("/getAllStores/{companyId}")
    public ResponseEntity<List<StoreResponseDto>> getAllStores(@PathVariable("companyId") String companyId) {
        return ResponseEntity.ok(storeService.getAllStore(companyId));
    }
    @GetMapping("/getStore/{store}")
    public ResponseEntity<StoreResponseDto> getStore(@PathVariable("storeId") String storeId) {
        return ResponseEntity.ok(storeService.getStore(storeId));
    }
    @PostMapping("/raise-request")
    public ResponseEntity<String> raiseRequest(@RequestBody Request request){
        return ResponseEntity.ok(requestService.raiseRequest(request));
    }
    @GetMapping("/get-requests/{companyId}")
    public ResponseEntity<List<Request>> getAllRequests(@PathVariable("companyId") String companyId) {
        return ResponseEntity.ok(requestService.getAllRequests(companyId));
    }

}
