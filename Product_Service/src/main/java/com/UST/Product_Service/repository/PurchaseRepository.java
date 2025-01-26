package com.UST.Product_Service.repository;

import com.UST.Product_Service.model.Purchases;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PurchaseRepository extends JpaRepository<Purchases,String> {
}
