package com.UST.Store_MicroService.model;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

@Entity
public class Request {
    @Id
    @GeneratedValue(generator = "request-id-generator")
    @GenericGenerator(
            name = "request-id-generator",
            strategy = "com.UST.Store_MicroService.generator.RequestIdGenerator"
    )
    private String requestId;
    private String companyId;
    private String storeId;
    private String storeName;
    private String productId;
    private String productName;
    private int quantity;
    private String status;
    private Date requestDate;
    private Date updatedAt;

    public Request() {
    }

    public Request(String companyId,String storeId, String storeName, String productId, String productName, int quantity, String status) {
        this.companyId = companyId;
        this.storeId = storeId;
        this.storeName = storeName;
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.status = status;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    @PrePersist
    protected void onCreate() {
        this.requestDate = new Date();
        this.updatedAt = new Date(); // Set updatedAt if necessary

    }
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Date();
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
