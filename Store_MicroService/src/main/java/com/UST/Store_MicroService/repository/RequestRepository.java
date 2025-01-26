package com.UST.Store_MicroService.repository;

import com.UST.Store_MicroService.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, String> {
       @Query("SELECT r from Request r where r.companyId=:companyId")
        List<Request> getAllRequests(@Param("companyId") String companyId);
    @Query("SELECT r from Request r where r.storeId=:storeId")
    List<Request> getAllRequestsByStore(@Param("storeId") String storeId);
}
