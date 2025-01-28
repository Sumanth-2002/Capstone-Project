package com.ust.Billing_Service.dto;

public class StoreSaledDto {
    private String storeId;
    private String storeName;
    private Double saleAmount;

    public StoreSaledDto() {
    }

    public StoreSaledDto(String storeId, String storeName, Double saleAmount) {
        this.storeId = storeId;
        this.storeName = storeName;
        this.saleAmount = saleAmount;
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

    public Double getSaleAmount() {
        return saleAmount;
    }

    public void setSaleAmount(Double saleAmount) {
        this.saleAmount = saleAmount;
    }
}
