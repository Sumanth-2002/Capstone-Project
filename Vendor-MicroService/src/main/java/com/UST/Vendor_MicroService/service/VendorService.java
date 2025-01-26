package com.UST.Vendor_MicroService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.UST.Vendor_MicroService.model.Vendor;
import com.UST.Vendor_MicroService.repository.VendorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class VendorService {

    @Autowired
    private VendorRepository vendorRepository;

    public List<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }

    public Optional<Vendor> getVendorById(String vendorID) {
        return vendorRepository.findById(vendorID);
    }

    public Vendor createVendor(Vendor vendor) {
        return vendorRepository.save(vendor);
    }

    public Vendor updateVendor(String vendorID, Vendor vendorDetails) {
        return vendorRepository.findById(vendorID).map(vendor -> {
            vendor.setCompanyID(vendorDetails.getCompanyID());
            vendor.setGstin(vendorDetails.getGstin());
            vendor.setVendorName(vendorDetails.getVendorName());
            vendor.setVendorAddress(vendorDetails.getVendorAddress());
            vendor.setContact(vendorDetails.getContact());
            vendor.setLastPurchased(vendorDetails.getLastPurchased());
            vendor.setQuantityPurchased(vendorDetails.getQuantityPurchased());
            return vendorRepository.save(vendor);
        }).orElseThrow(() -> new RuntimeException("Vendor not found with ID: " + vendorID));
    }

    public void deleteVendor(String vendorID) {
        vendorRepository.deleteById(vendorID);
    }
}