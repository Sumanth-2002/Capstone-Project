package com.UST.Product_Service.repository;

import com.UST.Product_Service.model.Purchases;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface PurchaseRepository extends JpaRepository<Purchases,String> {
    @Query("SELECT SUM(s.totalPrice) FROM Purchases s WHERE s.companyId = :companyId")
    Double getTotalPurchases(@Param("companyId") String companyId);
}
