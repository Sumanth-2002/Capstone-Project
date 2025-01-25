package com.UST.Store_MicroService.model;

import java.sql.Date;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
@Entity
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // private String storeId;
    private String productId;
    private Long quantity;
    private LocalDate lastUpdated;

    @OneToOne
    @JoinColumn(name = "store_id", referencedColumnName = "id",unique = true)
    private Store store;

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    // public String getStoreId() {
    //     return storeId;
    // }
    // public void setStoreId(String storeId) {
    //     this.storeId = storeId;
    // }
    public String getProductId() {
        return productId;
    }
    public void setProductId(String productId) {
        this.productId = productId;
    }
    public Long getQuantity() {
        return quantity;
    }
    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
    public LocalDate getLastUpdated() {
        return lastUpdated;
    }
    public void setLastUpdated(LocalDate lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
    public Inventory(Long id, String productId, Long quantity, LocalDate lastUpdated) {
        this.id = id;
        // this.storeId = storeId;
        this.productId = productId;
        this.quantity = quantity;
        this.lastUpdated = lastUpdated;
    }
    public Inventory() {
    }

}
