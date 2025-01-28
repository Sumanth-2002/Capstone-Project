package com.ust.Billing_Service.repository;

//import com.UST.Store_MicroService.dto.SalesDto;
import com.ust.Billing_Service.dto.SalesDto;
import com.ust.Billing_Service.dto.StoreSaledDto;
import com.ust.Billing_Service.dto.StoreYearlyDto;
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
    List<Object[]> getLeaderboard(@Param("storeId") String storeId);

    @Query(value = "SELECT s.store_id, s.store_name, SUM(b.total_price) AS totalSales " +
            "FROM billing b " +
            "JOIN Store s ON s.store_id = b.store_id " +
            "WHERE s.company_id = :companyId " +
            "GROUP BY s.store_id, s.store_name",
            nativeQuery = true)
    List<StoreSaledDto> getSales(@Param("companyId") String companyId);
    @Query(value = "SELECT  SUM(s.totalPrice) AS totalSales " +
            "FROM billing s " +
            "JOIN Store b ON s.store_id = b.store_id " +
            "WHERE b.company_id = :companyId ",
            nativeQuery = true)
    Double getTotalSales(@Param("companyId") String companyId);

    @Query("""
            SELECT MONTH(b.BillDate) as month, sum(b.totalPrice)
            FROM Billing b 
            WHERE YEAR(b.BillDate)= :year AND b.storeId = :storeId
            GROUP BY MONTH(b.BillDate)
            ORDER BY MONTH(b.BillDate)""")
    List<Object []> getStoreSalesYearly(@Param("storeId") String storeId, @Param("year") Integer year);
    @Query(value = """
    SELECT MONTH(b.billing_date) AS month, 
           COUNT(DISTINCT b.customer_id) AS customerCount 
    FROM billing b
    WHERE b.store_id = :storeId AND YEAR(b.billing_date) = :year
    GROUP BY MONTH(b.billing_date)
    ORDER BY MONTH(b.billing_date)
""", nativeQuery = true)
    List<Object[]> getCustomerForStore(@Param("storeId") String storeId, @Param("year") Integer year);

}