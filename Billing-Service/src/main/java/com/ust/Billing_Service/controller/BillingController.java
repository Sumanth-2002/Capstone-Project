package com.ust.Billing_Service.controller;

import com.ust.Billing_Service.dto.BillingDto;
import com.ust.Billing_Service.dto.SalesDto;
import com.ust.Billing_Service.dto.StoreSaledDto;
import com.ust.Billing_Service.dto.StoreYearlyDto;
import com.ust.Billing_Service.entity.Billing;
import com.ust.Billing_Service.entity.Customer;
import com.ust.Billing_Service.entity.ProductBilled;
import com.ust.Billing_Service.repository.ProductBilledRepository;
import com.ust.Billing_Service.service.BillingService;
import com.ust.Billing_Service.service.MetricsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/billing")
@CrossOrigin("*")
public class BillingController {

    @Autowired
    private BillingService billingService;

    @Autowired
    private MetricsService metricsService;

    @Autowired
    private ProductBilledRepository productBilledRepository;

    @PostMapping
    public ResponseEntity<String> addBilling(@RequestBody BillingDto billing) {

        return ResponseEntity.ok(billingService.addBillingData(billing));
    }

    @PostMapping("/customer")
    public ResponseEntity<String> addCustomer(@RequestBody Customer customer) {
        return ResponseEntity.ok(billingService.addCustomerData(customer));
    }

    @GetMapping("/get-leaderboard/{storeId}")
    public ResponseEntity<List<SalesDto>> getLeaderboard(@PathVariable String storeId) {
        return ResponseEntity.ok(billingService.getLeaderboard(storeId));
    }

    @GetMapping("/get-store-wise/{companyId}")
    public ResponseEntity<List<StoreSaledDto>> getStoreWise(@PathVariable String companyId) {
        return ResponseEntity.ok(metricsService.getStoresSales(companyId));
    }

    @GetMapping("/get-billing/{billingId}")
    public ResponseEntity<Billing> getBilling(@PathVariable String billingId) {
        return ResponseEntity.ok(billingService.getBillingByid(billingId));
    }

    @GetMapping("findProductsBybillingId/{billingId}")
    public ResponseEntity<List<ProductBilled>> getProductByBillingId(@PathVariable String billingId) {
        return ResponseEntity.ok(productBilledRepository.findAllByBillingId(billingId));
    }

    @GetMapping("/getSalesYearly/{storeId}")
    public List<StoreYearlyDto>  getProductStoreId(
            @PathVariable String storeId,
            @RequestParam Integer year) {
        return metricsService.getYearlyMetrics(storeId, year);
    }

    @GetMapping("/getProductsSelled/{storeId}")
    public List<Map<String,Object>> getProductSelled(@PathVariable String storeId){
        return metricsService.getProductSalesByStore(storeId);
    }

    @GetMapping("/get-customers/{storeId}")
    public List<Map<String, Object>> getCustomerByStoreId(@PathVariable String storeId,@RequestParam Integer year){
        return metricsService.getCustomerForStore(storeId,year);
    }

}
