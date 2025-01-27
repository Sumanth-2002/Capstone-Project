package com.ust.Billing_Service.repository;

import com.ust.Billing_Service.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository  extends JpaRepository<Customer,String> {

}
