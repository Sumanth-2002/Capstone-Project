package com.UST.Product_Service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name="product")
public class Product {
    @Id
    @GeneratedValue(generator = "product-id-generator")
    @GenericGenerator(
            name = "product-id-generator",
            strategy = "com.UST.Product_Service.generator.CustomIdGenerator"
    )
    private String productId;
    private String productName;
    private String vendorId;
    private String vendorName;
    private Double selling_Price;
    private Double cost_Price;
    private Long quantity;
    private String description;
    private String companyId;
    public Product(String productId, String productName,String vendorId, String vendorName, Double selling_Price, Double cost_Price, Long quantity, String description, String companyId) {
        this.productId = productId;
        this.productName = productName;
        this.vendorId = vendorId;
        this.vendorName = vendorName;
        this.selling_Price = selling_Price;
        this.cost_Price = cost_Price;
        this.quantity = quantity;
        this.description = description;
        this.companyId = companyId;
    }

    public Product() {
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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

    public Double getSelling_Price() {
        return selling_Price;
    }

    public void setSelling_Price(Double selling_Price) {
        this.selling_Price = selling_Price;
    }

    public Double getCost_Price() {
        return cost_Price;
    }

    public void setCost_Price(Double cost_Price) {
        this.cost_Price = cost_Price;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }
}
