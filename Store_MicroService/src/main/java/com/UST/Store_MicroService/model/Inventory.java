package com.UST.Store_MicroService.model;

import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.*;

@Entity
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String inventoryId;
    private String storeId;
    private String productId;
    private String productName;
    private int quantity;
    private LocalDate updatedAt;

    public Inventory() {
    }

    public Inventory(String inventoryId, String storeId, String productId, String productName, int quantity) {
        this.inventoryId = inventoryId;
        this.storeId = storeId;
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
    }
    @PrePersist
    protected void onCreate() {
        this.updatedAt = LocalDate.now();
    }
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDate.now();
    }

    public String getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(String inventoryId) {
        this.inventoryId = inventoryId;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
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

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }
}
