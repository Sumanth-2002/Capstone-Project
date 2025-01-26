package com.UST.Store_MicroService.dto;

import java.util.Date;
import java.util.List;

public class StoreResponseDto {
    private String storeId;
    private String companyId;
    private String storeName;
    private String region;
    private String storeAddress;
    private Date createdAt;
    private Long totalProducts;
    private List<InventoryDto> inventoryDtos;
    public StoreResponseDto() {}

    public StoreResponseDto(String storeId, String companyId, String storeName, String region, String storeAddress, Date createdAt, Long totalProducts, List<InventoryDto> inventoryDtos) {
        this.storeId = storeId;
        this.companyId = companyId;
        this.storeName = storeName;
        this.region = region;
        this.storeAddress = storeAddress;
        this.createdAt = createdAt;
        this.totalProducts = totalProducts;
        this.inventoryDtos = inventoryDtos;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Long getTotalProducts() {
        return totalProducts;
    }

    public void setTotalProducts(Long totalProducts) {
        this.totalProducts = totalProducts;
    }

    public List<InventoryDto> getInventoryDtos() {
        return inventoryDtos;
    }

    public void setInventoryDtos(List<InventoryDto> inventoryDtos) {
        this.inventoryDtos = inventoryDtos;
    }
}
