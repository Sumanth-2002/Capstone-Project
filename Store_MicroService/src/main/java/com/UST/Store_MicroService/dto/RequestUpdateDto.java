package com.UST.Store_MicroService.dto;


import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;


public class RequestUpdateDto {
    private String requestId;
    private String companyId;
    private String storeId;
    private String storeName;
    private String productId;
    private String productName;
    private String productDescription;
    private String category;
    private int quantity;



    public RequestUpdateDto() {
    }

    public RequestUpdateDto(String requestId, String companyId, String storeId, String storeName, String productId, String productName,String productDescription,String category,int quantity) {
        this.requestId = requestId;
        this.companyId = companyId;
        this.storeId = storeId;
        this.storeName = storeName;
        this.productId = productId;
        this.productName = productName;
        this.productDescription=productDescription;
        this.category=category;
        this.quantity = quantity;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
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
