package com.ust.Billing_Service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ust.Billing_Service.entity.ProductBilled;

import java.util.List;

public class BillingDto {
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
    private List<ProductBilled> productBilledList;

    public BillingDto() {
    }
    public BillingDto(String storeId,String storeName, String salesRepId, String customerId, String customerName, Double totalPrice, List<ProductBilled> productBilledList) {
        this.storeId = storeId;
        this.storeName = storeName;
        this.salesRepId = salesRepId;
        this.customerId = customerId;
        this.customerName = customerName;
        this.totalPrice = totalPrice;
        this.productBilledList = productBilledList;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
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

    public List<ProductBilled> getProductBilledList() {
        return productBilledList;
    }

    public void setProductBilledList(List<ProductBilled> productBilledList) {
        this.productBilledList = productBilledList;
    }
}
