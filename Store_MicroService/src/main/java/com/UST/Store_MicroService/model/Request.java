package com.UST.Store_MicroService.model;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
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
    private String status = "Requested";
    private LocalDate requestDate;
    private LocalDate updatedAt;

    public Request() {
    }

    public Request(String companyId,String storeId, String storeName, String productId, String productName, int quantity) {
        this.companyId = companyId;
        this.storeId = storeId;
        this.storeName = storeName;
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
    }

    public Request(int quantity, String productName, String productId, String storeName, String storeId, String companyId, String requestId) {
        this.quantity = quantity;
        this.productName = productName;
        this.productId = productId;
        this.storeName = storeName;
        this.storeId = storeId;
        this.companyId = companyId;
        this.requestId = requestId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    @PrePersist
    protected void onCreate() {
        this.requestDate = LocalDate.now();
        this.updatedAt = LocalDate.now(); // Set updatedAt if necessary

    }
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDate.now();
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

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }
}
