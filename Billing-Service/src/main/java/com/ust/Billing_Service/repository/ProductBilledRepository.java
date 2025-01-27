package com.ust.Billing_Service.repository;

import com.ust.Billing_Service.entity.ProductBilled;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductBilledRepository extends JpaRepository<ProductBilled,String> {
    @Query("SELECT p FROM ProductBilled p WHERE p.billingId = :billingId")
    List<ProductBilled> findAllByBillingId(@Param("billingId") String billingId);

}
