package com.UST.Store_MicroService.model;

import java.time.LocalDate;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Store {
    @Id
    @GeneratedValue(generator = "custom-id-generator")
    @GenericGenerator(
        name = "custom-id-generator",
        strategy = "com.UST.Store_MicroService.generator.CustomIdGenerator"
    )
    private String id;
    private String name;
    private String address;
    private String region;
    private String companyId;
    private LocalDate lastUpdated;

    @OneToOne(mappedBy = "store", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Inventory inventory;

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getRegion() {
        return region;
    }
    public void setRegion(String region) {
        this.region = region;
    }
    public String getCompanyId() {
        return companyId;
    }
    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }
    public LocalDate getLastUpdated() {
        return lastUpdated;
    }
    public void setLastUpdated(LocalDate lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
    public Store(String id, String name, String address, String region, String companyId, LocalDate lastUpdated) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.region = region;
        this.companyId = companyId;
        this.lastUpdated = lastUpdated;
    }
    public Store() {
    }

}
