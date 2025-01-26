package com.ust.Billing_Service.repository;

import com.ust.Billing_Service.entity.SalesRep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesRepRepository extends JpaRepository<SalesRep, String> {
}
