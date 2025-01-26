package com.UST.Store_MicroService.repository;

import com.UST.Store_MicroService.model.SalesRep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesRepRepository extends JpaRepository<SalesRep,String> {
}
