package com.ust.Company_Service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CompanyDto {


    @JsonProperty("GSTIN")
    private String GSTIN;

    @JsonProperty("name")
    private String name;
    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;

    public CompanyDto() {
    }

    public CompanyDto(String GSTIN, String name, String email, String password) {
        this.GSTIN = GSTIN;

        this.name = name;
        this.email = email;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
