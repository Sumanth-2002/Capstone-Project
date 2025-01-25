package com.ust.Billing_Service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Customer {
    @Id
    @GeneratedValue(generator = "customer-id-generator")
    @GenericGenerator(
            name = "customer-id-generator",
            strategy = "com.ust.Billing_Service.generator.CustomerIdGenerator"
    )
    private  String customerId;
    private String customerName;
    private  String contact;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Customer(String customerName, String contact) {
        this.customerName = customerName;
        this.contact = contact;
    }

    public Customer() {
    }
}
