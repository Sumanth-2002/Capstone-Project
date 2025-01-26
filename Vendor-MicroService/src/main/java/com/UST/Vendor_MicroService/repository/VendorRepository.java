package com.UST.Vendor_MicroService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.UST.Vendor_MicroService.model.Vendor;

public interface VendorRepository extends JpaRepository<Vendor,String>{

}
