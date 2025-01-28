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
    @Query("""
        SELECT p.productName, SUM(p.quantity) 
        FROM ProductBilled p 
        JOIN Billing b ON b.billingId = p.billingId 
        WHERE b.storeId = :storeId 
        GROUP BY p.productId, p.productName
       """)
    List<Object[]> getProductsByStoreWise(@Param("storeId") String storeId);
    @Query(value="""
        SELECT p.product_name, SUM(p.quantity) 
        FROM product_billed p 
        JOIN billing b ON b.billing_id = p.billing_id 
        WHERE b.store_id IN (SELECT s.store_id FROM Store s WHERE s.company_id = :companyId)
        GROUP BY p.product_id, p.product_name
       """,nativeQuery = true)
    List<Object[]> getProductByCompanyId(@Param("companyId") String companyId);



}
