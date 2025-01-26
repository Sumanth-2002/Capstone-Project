package com.UST.Store_MicroService.repository;

import com.UST.Store_MicroService.model.SalesRep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesRepRepository extends JpaRepository<SalesRep,String> {
    @Query("SELECT s from SalesRep s where s.storeId= :storeId")
    List<SalesRep> getAllSalesRep(@Param("storeId") String storeId);
}
