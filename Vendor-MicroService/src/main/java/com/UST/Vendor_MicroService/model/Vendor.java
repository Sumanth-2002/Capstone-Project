package com.UST.Vendor_MicroService.model;


import jakarta.persistence.*;
import java.time.LocalDate;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "vendors")
public class Vendor {

    @Id
    @GeneratedValue(generator = "custom-id-generator")
    @GenericGenerator(
        name = "custom-id-generator",
        strategy = "com.UST.Vendor_MicroService.generator.CustomIdGenerator"
    )
    private String vendorID;

    private String companyId;

    private String gstin;

    private String vendorName;

    private String vendorAddress;

    private String contact;
    private String email;
    private LocalDate lastPurchased;

    private Long quantityPurchased=0L;

    public String getVendorID() {
        return vendorID;
    }

    public void setVendorID(String vendorID) {
        this.vendorID = vendorID;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getGstin() {
        return gstin;
    }

    public void setGstin(String gstin) {
        this.gstin = gstin;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorAddress() {
        return vendorAddress;
    }

    public void setVendorAddress(String vendorAddress) {
        this.vendorAddress = vendorAddress;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public LocalDate getLastPurchased() {
        return lastPurchased;
    }

    public void setLastPurchased(LocalDate lastPurchased) {
        this.lastPurchased = lastPurchased;
    }

    public Long getQuantityPurchased() {
        return quantityPurchased;
    }

    public void setQuantityPurchased(Long quantityPurchased) {
        this.quantityPurchased = quantityPurchased;
    }

    public Vendor(String companyId, String gstin, String vendorName, String vendorAddress,
            String contact,String email) {
        this.companyId = companyId;
        this.gstin = gstin;
        this.vendorName = vendorName;
        this.vendorAddress = vendorAddress;
        this.contact = contact;

        this.email = email;
    }

    public Vendor() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @PrePersist
    private void onCreate(){
        this.lastPurchased = LocalDate.now();
    }
    @PreUpdate
    private void onUpdate(){
        this.lastPurchased = LocalDate.now();
    }
}