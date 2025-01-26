package com.UST.Store_MicroService.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class SalesRep {
    @Id
    @GeneratedValue(generator = "rep-id-generator")
    @GenericGenerator(
            name = "rep-id-generator",
            strategy = "com.UST.Store_MicroService.generator.RepIdGenerator"
    )
    private String salesRepId;
    private String storeId;
    private String name;
    private String contact;

    public SalesRep(String storeId, String name, String contact) {
        this.storeId = storeId;
        this.name = name;
        this.contact = contact;
    }

    public SalesRep() {
    }

    public String getSalesRepId() {
        return salesRepId;
    }

    public void setSalesRepId(String salesRepId) {
        this.salesRepId = salesRepId;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}

