package com.ust.Billing_Service.dto;

public class SalesDto {
    private String salesRepId;
    private String salesRepName;
    private Double  noofSales;

    public SalesDto(String salesRepId, String salesRepName, Double noofSales) {
        this.salesRepId = salesRepId;
        this.salesRepName = salesRepName;
        this.noofSales = noofSales;
    }

    public SalesDto() {
    }

    public String getSalesRepId() {
        return salesRepId;
    }

    public void setSalesRepId(String salesRepId) {
        this.salesRepId = salesRepId;
    }

    public String getSalesRepName() {
        return salesRepName;
    }

    public void setSalesRepName(String salesRepName) {
        this.salesRepName = salesRepName;
    }

    public Double getNoofSales() {
        return noofSales;
    }

    public void setNoofSales(Double noofSales) {
        this.noofSales = noofSales;
    }
}
