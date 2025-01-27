package com.UST.Vendor_MicroService.controller;

import com.UST.Vendor_MicroService.dto.VendorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.UST.Vendor_MicroService.model.Vendor;
import com.UST.Vendor_MicroService.service.VendorService;

import java.util.List;

@RestController
@RequestMapping("/api/vendors")
public class VendorController {

    @Autowired
    private VendorService vendorService;

    @GetMapping
    public List<Vendor> getAllVendors() {
        return vendorService.getAllVendors();
    }

    @GetMapping("/{vendorID}")
    public ResponseEntity<Vendor> getVendorById(@PathVariable String vendorID) {
        return vendorService.getVendorById(vendorID)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Vendor createVendor(@RequestBody Vendor vendor) {
        return vendorService.createVendor(vendor);
    }

    @PutMapping("/update-vendor")
    public ResponseEntity<Vendor> updateVendor(@RequestBody VendorDto vendorDto) {
        try {
            return ResponseEntity.ok(vendorService.updateVendor(vendorDto));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{vendorID}")
    public ResponseEntity<Void> deleteVendor(@PathVariable String vendorID) {
        vendorService.deleteVendor(vendorID);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/get-vendor/{companyId}")
    public ResponseEntity<List<Vendor>> getVendorByCompanyId(@PathVariable String companyId) {
        return ResponseEntity.ok(vendorService.getVendorByCompanyID(companyId));
    }
}