package com.ust.Billing_Service.dto;

public class ProductDto {
    private String productName;
    private String productId;
    private double price;
    private int quantity;
    private double subtotal;

    // Constructor
    public ProductDto(String productName, String productId, double price, int quantity, double subtotal) {
        this.productName = productName;
        this.productId = productId;
        this.price = price;
        this.quantity = quantity;
        this.subtotal = subtotal;
    }

    // Getters
    public String getProductName() {
        return productName;
    }

    public String getProductId() {
        return productId;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getSubtotal() {
        return subtotal;
    }
}
