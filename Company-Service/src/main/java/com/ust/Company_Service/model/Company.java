package com.ust.Company_Service.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Company {

    @Id
    @GeneratedValue(generator = "custom-id-generator")
    @GenericGenerator(
            name = "custom-id-generator",
            strategy = "com.ust.Company_Service.CustomIdGenerator"
    )
    private String companyId;

    @JsonProperty("GSTIN")
    private String GSTIN;

    @JsonProperty("name")
    private String name;
    @JsonProperty("email")
    private String email;


    public Company() {
    }

    public Company(String GSTIN,  String name, String email) {
        this.GSTIN = GSTIN;

        this.name = name;
        this.email = email;

    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getGSTIN() {
        return GSTIN;
    }

    public void setGSTIN(String GSTIN) {
        this.GSTIN = GSTIN;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    }

