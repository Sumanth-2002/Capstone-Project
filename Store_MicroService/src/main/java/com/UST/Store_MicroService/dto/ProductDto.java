package com.UST.Store_MicroService.dto;



public class ProductDto {
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

    public ProductDto(String productId, String productName,Long quantity) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
    }

    public ProductDto() {
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    private String productId;
    private String productName;
    private Long quantity;
}

