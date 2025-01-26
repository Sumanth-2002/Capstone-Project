package com.UST.Vendor_MicroService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.UST.Vendor_MicroService.model.Vendor;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorRepository extends JpaRepository<Vendor,String>{

}
