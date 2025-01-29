package com.UST.Store_MicroService.dto;


public class StoreDto {
    private String companyId;
    private String storeName;
    private String region;
    private String storeAddress;
    private String productId;
    private String productName;
    private String productDescription;
    private String category;
    private int quantity;

    public StoreDto() {
    }

    public StoreDto(String companyId, String storeName, String region, String storeAddress, String productId, String productName, String productDescription,String category,int quantity) {
        this.companyId = companyId;
        this.storeName = storeName;
        this.region = region;
        this.storeAddress = storeAddress;
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.category = category;
        this.quantity = quantity;
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

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
