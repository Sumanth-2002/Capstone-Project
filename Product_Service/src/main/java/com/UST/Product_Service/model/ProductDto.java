package com.UST.Product_Service.model;

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

    public ProductDto(String productId, Long quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public ProductDto() {
    }

    private String productId;
    private Long quantity;
}
