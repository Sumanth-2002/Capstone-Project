package com.ust.Billing_Service.repository;

//import com.UST.Store_MicroService.dto.SalesDto;
import com.ust.Billing_Service.dto.SalesDto;
import com.ust.Billing_Service.dto.StoreSaledDto;
import com.ust.Billing_Service.entity.Billing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BillingRepository extends JpaRepository<Billing, String> {

    @Query(value = "SELECT s.sales_rep_id AS salesRepId, s.name AS salesRepName, COUNT(b.sales_rep_id) AS salesCount " +
            "FROM billing b " +
            "JOIN sales_rep s ON b.sales_rep_id = s.sales_rep_id " +
            "WHERE b.store_id = :storeId " +
            "GROUP BY s.sales_rep_id, s.name " +
            "ORDER BY COUNT(b.sales_rep_id) DESC " +
            "LIMIT 10", nativeQuery = true)
    List<SalesDto> getLeaderboard(@Param("storeId") String storeId);

    @Query(value = "SELECT s.storeId, s.name, SUM(s.totalPrice) AS totalSales " +
            "FROM billing s " +
            "JOIN Store b ON s.storeId = b.storeId " +
            "WHERE b.companyId = :companyId " +
            "GROUP BY s.storeId, s.name",
            nativeQuery = true)
    List<StoreSaledDto> getSales(@Param("companyId") String companyId);
    @Query(value = "SELECT  SUM(s.totalPrice) AS totalSales " +
            "FROM billing s " +
            "JOIN Store b ON s.storeId = b.storeId " +
            "WHERE b.companyId = :companyId ",
            nativeQuery = true)
    Double getTotalSales(@Param("companyId") String companyId);

}