package com.ust.Billing_Service.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.List;

@Entity
public class Billing {
    @Id
    @GeneratedValue(generator = "custom-id-generator")
    @GenericGenerator(
            name = "custom-id-generator",
            strategy = "com.ust.Billing_Service.generator.CustomIdGenerator"
    )
    private String billingId;

    @JsonProperty("storeId")
    private String storeId;
    @JsonProperty("storeName")
    private String storeName;
    @JsonProperty("salesRepId")
    private String salesRepId;

    @JsonProperty("customerId")
    private String customerId;

    @JsonProperty("customerName")
    private String customerName;

    @JsonProperty("totalPrice")
    private Double totalPrice;

    private Date BillDate;

    // Removed @OneToMany and the products list
    public Billing() {}

    public Billing(String storeId, String storeName,String salesRepId, String customerId, String customerName, Double totalPrice) {
        this.storeId = storeId;
        this.storeName = storeName;
        this.salesRepId = salesRepId;
        this.customerId = customerId;
        this.customerName = customerName;
        this.totalPrice = totalPrice;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getBillingId() {
        return billingId;
    }

    public void setBillingId(String billingId) {
        this.billingId = billingId;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getSalesRepId() {
        return salesRepId;
    }

    public void setSalesRepId(String salesRepId) {
        this.salesRepId = salesRepId;
    }

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

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @PrePersist
    public void onCreate() {
        this.BillDate = new Date();
    }
    public Date getBillDate() {return BillDate;}
    public void setBillDate(Date billDate) {this.BillDate = billDate;};

}
