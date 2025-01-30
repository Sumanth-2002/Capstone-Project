package com.UST.Store_MicroService.controller;

import com.UST.Store_MicroService.dto.*;
import com.UST.Store_MicroService.model.Inventory;
import com.UST.Store_MicroService.model.Request;
//import com.UST.Store_MicroService.model.SalesRep;
import com.UST.Store_MicroService.model.SalesRep;
import com.UST.Store_MicroService.model.Store;
import com.UST.Store_MicroService.service.RequestService;
//import com.UST.Store_MicroService.service.SalesRepService;
import com.UST.Store_MicroService.service.SalesRepService;
import com.UST.Store_MicroService.service.StoreService;
//import com.UST.Store_MicroService.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stores")
@CrossOrigin("*")
public class StoreInventoryController {

    @Autowired
    private StoreService storeService;
    @Autowired
    private RequestService requestService;
//
    @Autowired
    private SalesRepService salesRepService;

    @PostMapping("/addStore")
    public ResponseEntity<String> addStore(@RequestBody StoreDto storeDto) {
        return ResponseEntity.ok(storeService.addStoreInventory(storeDto));
    }


    @GetMapping("/getAllStores/{companyId}")
    public ResponseEntity<List<StoreResponseDto>> getAllStores(@PathVariable("companyId") String companyId) {
        return ResponseEntity.ok(storeService.getAllStore(companyId));
    }
    @GetMapping("/getStore/{storeId}")
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
//
    @PostMapping("/add-sales-rep")
    public ResponseEntity<SalesRep> addSalesRep(SalesRep salesRep){
        return ResponseEntity.ok(salesRepService.addSalesRep(salesRep));
    }
    @GetMapping("/get-all-salesRep/{storeId}")
    public ResponseEntity<List<SalesRep>> getAllSalesRep(@PathVariable String storeId) {
        return ResponseEntity.ok(salesRepService.getAllSalesRep(storeId));
    }
    @GetMapping("/getAllStorealone/{companyId}")
    public ResponseEntity<List<Store>> getAllStoreAlone(@PathVariable("companyId") String companyId){
        return ResponseEntity.ok(storeService.getAllStoreAlone(companyId));
    }

    @PutMapping("/update-stock")
    public ResponseEntity<String> updateStore(@RequestBody UpdateProductDto updateProductDto){
        storeService.updateStore(updateProductDto);
        return ResponseEntity.ok("Product Details  updated Successfully");
    }

    @GetMapping("/get-all-requests/store/{storeId}")
    public ResponseEntity<List<Request>> getAllStoreRequests(@PathVariable("storeId") String storeId) {
        return ResponseEntity.ok(requestService.getAllRequestsForStore(storeId));
    }
    @PutMapping("/update-request")
    public ResponseEntity<Request> updateRequest(@RequestBody RequestUpdateDto requestUpdateDto){
        return ResponseEntity.ok(requestService.updateRequest(requestUpdateDto));
    }
    @PutMapping("/restock-products")
    public ResponseEntity<String> restockProduct(@RequestBody UpdateProductDto updateProductDto){
        if(storeService.updateQuantity(updateProductDto)==null) throw  new RuntimeException("Error while restocking products");
        return ResponseEntity.ok("Product Details updated Successfully");
    }

    @GetMapping("/get-store-products/{storeId}")
    public List<InventoryDto> getStoreProducts(@PathVariable("storeId") String storeId){
        return storeService.getAllProductsByStore(storeId);
    }
}
