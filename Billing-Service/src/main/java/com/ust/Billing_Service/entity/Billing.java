package com.ust.Billing_Service.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Billing {
    @Id
    @GeneratedValue(generator = "custom-id-generator")
    @GenericGenerator(
            name = "custom-id-generator",
            strategy = "com.ust.Billing_Service.generator.CustomIdGenerator"
    )
    private  String billingId;
    @JsonProperty("storeId")
    private String storeId;

    @JsonProperty("salesRepId")
    private String salesRepId;

    @JsonProperty("customerId")
    private String customerId;

    @JsonProperty("customerName")
    private String customerName;

    @JsonProperty("productId")
    private String productId;

    @JsonProperty("productName")
    private String productName;

    @JsonProperty("price")
    private Double price;

    @JsonProperty("GST")
    private Double GST;

    @JsonProperty("totalPrice")
    private Double totalPrice;

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

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getGST() {
        return GST;
    }

    public void setGST(Double GST) {
        this.GST = GST;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Billing() {
    }

    public Billing(String storeId, String salesRepId, String customerId, String customerName, String productId, String productName, Double price, Double GST, Double totalPrice) {
        this.storeId = storeId;
        this.salesRepId = salesRepId;
        this.customerId = customerId;
        this.customerName = customerName;
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.GST = GST;
        this.totalPrice = totalPrice;
    }
    @Override
    public String toString() {
        return "Billing{" +
                "billingId='" + billingId + '\'' +
                ", storeId='" + storeId + '\'' +
                ", salesRepId='" + salesRepId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", GST=" + GST +
                ", totalPrice=" + totalPrice +
                '}';
    }

}
