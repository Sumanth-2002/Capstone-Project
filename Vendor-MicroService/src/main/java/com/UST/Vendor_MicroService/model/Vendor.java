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

    private String companyID;

    private String gstin;

    private String vendorName;

    private String vendorAddress;

    private String contact;

    private LocalDate lastPurchased;

    private Long quantityPurchased;

    public String getVendorID() {
        return vendorID;
    }

    public void setVendorID(String vendorID) {
        this.vendorID = vendorID;
    }

    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
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

    public Vendor(String companyID, String gstin, String vendorName, String vendorAddress,
            String contact, LocalDate lastPurchased, Long quantityPurchased) {
        this.companyID = companyID;
        this.gstin = gstin;
        this.vendorName = vendorName;
        this.vendorAddress = vendorAddress;
        this.contact = contact;
        this.lastPurchased = lastPurchased;
        this.quantityPurchased = quantityPurchased;
    }

    public Vendor() {
    }
}