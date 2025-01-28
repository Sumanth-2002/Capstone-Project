package com.UST.Vendor_MicroService.service;

import com.UST.Vendor_MicroService.dto.VendorDto;
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

    public List<Vendor> getAllVendors(String companyId) {
        return vendorRepository.findByCompanyId(companyId);
    }

    public Optional<Vendor> getVendorById(String vendorID) {
        return vendorRepository.findById(vendorID);
    }

    public Vendor createVendor(Vendor vendor) {
        return vendorRepository.save(vendor);
    }

    public Vendor updateVendor(VendorDto vendorDto) {
        return vendorRepository.findById(vendorDto.getVendorId()).map(vendor -> {


            vendor.setQuantityPurchased(vendor.getQuantityPurchased()+vendorDto.getQuantity());
            return vendorRepository.save(vendor);
        }).orElseThrow(() -> new RuntimeException("Vendor not found with ID: " + vendorDto.getVendorId()));
    }

    public void deleteVendor(String vendorID) {
        vendorRepository.deleteById(vendorID);
    }

    public List<Vendor> getVendorByCompanyID(String companyID) {
        return vendorRepository.findByCompanyId(companyID);
    }
}