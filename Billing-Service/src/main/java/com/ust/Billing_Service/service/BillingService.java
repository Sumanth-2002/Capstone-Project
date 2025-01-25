package com.ust.Billing_Service.service;

import com.ust.Billing_Service.entity.Billing;
import com.ust.Billing_Service.entity.Customer;
import com.ust.Billing_Service.repository.BillingRepository;
import com.ust.Billing_Service.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillingService {
    @Autowired
    private BillingRepository billingRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public Billing addBillingData(Billing billing){
        return billingRepository.save(billing);
    }

    public String addCustomerData(Customer customer){
        customerRepository.save(customer);
        return  customer.getCustomerId();
    }
}
