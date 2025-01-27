package com.UST.Vendor_MicroService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.UST.Vendor_MicroService.model.Vendor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendorRepository extends JpaRepository<Vendor,String>{

    List<Vendor> findByCompanyId(String companyId);
}
