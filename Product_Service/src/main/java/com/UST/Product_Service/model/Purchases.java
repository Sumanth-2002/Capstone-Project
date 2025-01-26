package com.UST.Product_Service.model;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
@Entity
public class Purchases {
    @Id
    @GeneratedValue(generator = "purchase-id-generator")
    @GenericGenerator(
            name = "purchase-id-generator",
            strategy = "com.UST.Product_Service.generator.PurchaseIdGenerator"
    )
    private String purchaseId;
    private String companyId;
    private String productId;
    private String productName;
    private String vendorId;
    private String vendorName;
    private Long  quantity;
    private Double price;
    private Double totalPrice;
    private Date purchaseDate;

    public Purchases(String companyId,String productId, String productName, String vendorId, String vendorName, Long quantity, Double price, Double totalPrice, Date purchaseDate) {
        this.companyId = companyId;
        this.productId = productId;
        this.productName = productName;
        this.vendorId = vendorId;
        this.vendorName = vendorName;
        this.quantity = quantity;
        this.price = price;
        this.totalPrice = totalPrice;
        this.purchaseDate = purchaseDate;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public Purchases() {
    }

    public String getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(String purchaseId) {
        this.purchaseId = purchaseId;
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

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    @PrePersist
    private void onCreate(){
        this.purchaseDate = new Date();
    }

    @PreUpdate
    private void onUpdate(){
        this.purchaseDate = new Date();
    }
}
